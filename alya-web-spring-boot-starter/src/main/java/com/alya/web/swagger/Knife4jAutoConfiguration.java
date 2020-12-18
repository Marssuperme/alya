package com.alya.web.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author alya
 */
@Configuration
public class Knife4jAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Knife4jProperties knife4jProperties() {
        return new Knife4jProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public Docket createDocket(Knife4jProperties properties) {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        if (Boolean.FALSE.equals(properties.getEnable())) {
            return docket.enable(false);
        } else {
            ApiInfo apiInfo = new ApiInfoBuilder()
                    .title(properties.getTitle())
                    .description(properties.getDescription())
                    .version(properties.getVersion())
                    .license(properties.getLicense())
                    .licenseUrl(properties.getLicenseUrl())
                    .contact(new Contact(properties.getContact().getName(), properties.getContact().getUrl(), properties.getContact().getEmail()))
                    .termsOfServiceUrl(properties.getTermsOfServiceUrl())
                    .build();
            return docket
                    .apiInfo(apiInfo)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                    .paths(PathSelectors.any())
                    .build();
        }
    }

}
