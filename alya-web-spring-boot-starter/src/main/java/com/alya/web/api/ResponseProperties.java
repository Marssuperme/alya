package com.alya.web.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @author alya
 */
@Configuration
@ConfigurationProperties(prefix = "alya.web")
public class ResponseProperties {

    private Boolean defaultResponse;

    public Boolean getDefaultResponse() {
        return defaultResponse;
    }

    public void setDefaultResponse(Boolean defaultResponse) {
        this.defaultResponse = defaultResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseProperties that = (ResponseProperties) o;
        return Objects.equals(defaultResponse, that.defaultResponse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(defaultResponse);
    }

    @Override
    public String toString() {
        return "ResponseProperties{" +
                "defaultResponse=" + defaultResponse +
                '}';
    }
}
