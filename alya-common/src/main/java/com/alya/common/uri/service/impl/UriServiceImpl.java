package com.alya.common.uri.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alya.common.anno.Method;
import com.alya.common.uri.service.IUriService;
import com.alya.common.vo.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author I‘m
 */
@Service("iUriService")
public class UriServiceImpl implements IUriService {

    private final WebApplicationContext webApplicationContext;

    @Autowired
    public UriServiceImpl(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @Override
    public List<UriInfo> uris() {
        List<UriInfo> list = new ArrayList<>();
        RequestMappingHandlerMapping mapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            String className = entry.getValue().getMethod().getDeclaringClass().getName();
            if (ObjectUtil.isEmpty(className)) {
                continue;
            }
            // 一个方法可能对应多个url
            PatternsRequestCondition patternsCondition = entry.getKey().getPatternsCondition();
            if (null != patternsCondition) {
                for (String url : patternsCondition.getPatterns()) {
                    UriInfo uriInfo = new UriInfo();
                    uriInfo.setUri(getUri(url));
                    uriInfo.setServer(applicationName());
                    uriInfo.setType(getType(entry.getKey().getMethodsCondition()));
                    getMethodName(entry.getValue(), uriInfo);
                    list.add(uriInfo);
                }
            }
        }
        return list.stream().distinct().collect(Collectors.toList());
    }

    private void getMethodName(HandlerMethod handlerMethod, UriInfo uriInfo) {
        String name = "";
        String remark = "";
        Method method = handlerMethod.getMethodAnnotation(Method.class);
        if (handlerMethod.hasMethodAnnotation(Method.class) && null != method) {
            name = method.name();
            remark = method.remark();
        }
        uriInfo.setName(name);
        uriInfo.setRemark(remark);
    }

    private String getUri(String url) {
        String separator = "/";
        String separator2 = "//";
        String separator3 = "///";
        String contextPath = webApplicationContext.getEnvironment().getProperty("server.servlet.context-path");
        String uri;
        if (!ObjectUtil.isEmpty(contextPath) && !separator.equals(contextPath)) {
            uri = contextPath + url;
            if (uri.contains(separator3) || uri.contains(separator2)) {
                uri = uri.replace(separator3, separator);
                uri = uri.replace(separator2, separator);
            }
        } else {
            uri = url;
        }
        return uri;
    }

    private String getType(RequestMethodsRequestCondition methodsCondition) {
        // 如果多个，改为一个POST
        String type = null;
        int count = 0;
        for (RequestMethod requestMethod : methodsCondition.getMethods()) {
            type = requestMethod.toString();
            count ++;
        }
        return type == null || count > 1 ? "POST" : type;
    }

    @Override
    public String applicationName() {
        return webApplicationContext.getEnvironment().getProperty("spring.application.name");
    }
}
