package com.alya.common.comonent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author I‘m
 */
@Component
public class NacosContextPathMetadataRegister implements EnvironmentPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(NacosContextPathMetadataRegister.class);
    private static final String SEPARATOR = "/";
    private static final String KEY_SERVLET_CONTEXT_PATH = "server.servlet.context-path";
    private static final String KEY_NACOS_SERVLET_CONTEXT_PATH = "spring.cloud.nacos.discovery.metadata.server.servlet.context-path";
    private static final String PROPERTY_SOURCE_NAME = "defaultProperties";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String contextPath = environment.getProperty(KEY_SERVLET_CONTEXT_PATH);
        if (null != contextPath && !"".equals(contextPath) && !SEPARATOR.equals(contextPath)) {
            Map<String, Object> map = new HashMap<>(2);
            map.put(KEY_NACOS_SERVLET_CONTEXT_PATH, contextPath);
            this.addOrReplace(environment.getPropertySources(), map);
            LOGGER.info("add or replace env properties: {}", map);
        }
    }

    private void addOrReplace(MutablePropertySources propertySources, Map<String, Object> map) {
        MapPropertySource target = null;
        if (propertySources.contains(PROPERTY_SOURCE_NAME)) {
            PropertySource<?> source = propertySources.get(PROPERTY_SOURCE_NAME);
            if (source instanceof MapPropertySource) {
                target = (MapPropertySource) source;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    if (!target.containsProperty(key)) {
                        target.getSource().put(key, map.get(key));
                    }
                }
            }
        }

        if (target == null) {
            target = new MapPropertySource(PROPERTY_SOURCE_NAME, map);
        }

        if (!propertySources.contains(PROPERTY_SOURCE_NAME)) {
            propertySources.addLast(target);
        }

    }

}
