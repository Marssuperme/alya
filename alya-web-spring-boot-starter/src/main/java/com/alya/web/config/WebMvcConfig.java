package com.alya.web.config;

import com.alya.web.api.ResponseProperties;
import com.alya.web.interceptor.ResponseResultInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Objects;

/**
 * @author alya
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcConfig.class);

    @Bean
    @ConditionalOnMissingBean
    public ResponseProperties responseProperties() {
        return new ResponseProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public HandlerInterceptor responseResultInterceptor() {
        return new ResponseResultInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        boolean flag = false;
        ResponseProperties responseProperties = this.responseProperties();
        if (Objects.nonNull(responseProperties) && Boolean.TRUE.equals(responseProperties.getDefaultResponse())) {
            registry.addInterceptor(this.responseResultInterceptor()).addPathPatterns("/**");
            flag = true;
        }
        LOGGER.info("Enable Default Response: {}", flag);
    }

}