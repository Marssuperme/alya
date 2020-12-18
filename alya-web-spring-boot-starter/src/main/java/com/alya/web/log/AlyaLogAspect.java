package com.alya.web.log;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * log aspect
 *
 * @author alya
 * @see AlyaLog
 */
@Aspect
@Component
public class AlyaLogAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(AlyaLogAspect.class);

    @Pointcut("@annotation(com.alya.web.log.AlyaLog)")
    public void log() {
        // do nothing
    }

    @Before("log()")
    public void beforeLog(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        if (point.getThis().getClass() != point.getTarget().getClass()) {
            method = ReflectUtil.getMethod(point.getTarget().getClass(), method.getName(), method.getParameterTypes());
        }

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        // headers information
        Map<String, Object> headerMap = new HashMap<>(16);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerKey = headerNames.nextElement();
            String headerValue = request.getHeader(headerKey);
            headerMap.put(headerKey, headerValue);
        }

        Map<String, Object> parameterMap = new HashMap<>(16);
        // parameter
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
        if (ArrayUtil.isNotEmpty(parameterNames)) {
            // parameter values
            Object[] parameter = point.getArgs();
            for (String parameterName : parameterNames) {
                for (Object o : parameter) {
                    parameterMap.put(parameterName, o);
                }
            }
        }
        LOGGER.info("Alya log: method={}, uri={}, header={}, param={}", request.getMethod(), request.getRequestURI(), headerMap, parameterMap);
    }

}
