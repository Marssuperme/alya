package com.alya.core.interceptor;

import com.alya.core.api.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Before enter request method, if this class or method had @{code RestResult} annotation,
 * add default tag to request attribute so that method could return default response result.
 *
 * @author gfye
 */
@Component("responseResultInterceptor")
public class ResponseResultInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseResultInterceptor.class);
    public static final String RESPONSE_RESULT_TAG = "RESPONSE_RESULT_TAG";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> clazz = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(RestResult.class)) {
                LOGGER.debug("Add class tag: {}", clazz);
                request.setAttribute(RESPONSE_RESULT_TAG, clazz.getAnnotation(RestResult.class));
            } else if (method.isAnnotationPresent(RestResult.class)) {
                LOGGER.debug("Add method tag: {}", method);
                request.setAttribute(RESPONSE_RESULT_TAG, method.getAnnotation(RestResult.class));
            }
        }
        return true;
    }
}
