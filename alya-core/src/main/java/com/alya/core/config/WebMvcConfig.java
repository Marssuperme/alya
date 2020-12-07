package com.alya.core.config;

import com.alya.core.interceptor.ResponseResultInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author alya
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcConfig.class);

    @Value("${alya.web.default-response}")
    private boolean defaultResponse;

    private final ResponseResultInterceptor responseResultInterceptor;

    public WebMvcConfig(ResponseResultInterceptor responseResultInterceptor) {
        this.responseResultInterceptor = responseResultInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LOGGER.info("Enable Default Response: {}", defaultResponse);
        if (defaultResponse) {
            registry.addInterceptor(responseResultInterceptor).addPathPatterns("/**");
        }
    }

}
