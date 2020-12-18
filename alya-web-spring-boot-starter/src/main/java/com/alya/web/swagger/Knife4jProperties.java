package com.alya.web.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @author alya
 */
@Configuration
@ConfigurationProperties(prefix = "alya.swagger")
public class Knife4jProperties {

    private Boolean enable = false;
    private String title = "";
    private String description = "";
    private String termsOfServiceUrl = "";
    private Contact contact = new Contact();
    private String version = "";
    private String license = "";
    private String licenseUrl = "";
    private String basePackage = "";

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Knife4jProperties that = (Knife4jProperties) o;
        return Objects.equals(enable, that.enable)
                && Objects.equals(title, that.title)
                && Objects.equals(description, that.description)
                && Objects.equals(termsOfServiceUrl, that.termsOfServiceUrl)
                && Objects.equals(contact, that.contact)
                && Objects.equals(version, that.version)
                && Objects.equals(license, that.license)
                && Objects.equals(licenseUrl, that.licenseUrl)
                && Objects.equals(basePackage, that.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enable, title, description, termsOfServiceUrl, contact, version, license, licenseUrl, basePackage);
    }

    @Override
    public String toString() {
        return "Knife4jProperties{" +
                "enable=" + enable +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", termsOfServiceUrl='" + termsOfServiceUrl + '\'' +
                ", contact=" + contact +
                ", version='" + version + '\'' +
                ", license='" + license + '\'' +
                ", licenseUrl='" + licenseUrl + '\'' +
                ", basePackage='" + basePackage + '\'' +
                '}';
    }

    public static class Contact {
        private String name = "";
        private String url = "";
        private String email = "";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Contact contact = (Contact) o;
            return Objects.equals(name, contact.name)
                    && Objects.equals(url, contact.url)
                    && Objects.equals(email, contact.email);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, url, email);
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
}
