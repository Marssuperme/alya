package com.alya.common.vo;

import java.util.Objects;

public class UriInfo {

    private String uri;

    private String name;

    private String remark;

    private String type;

    private String server;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UriInfo uriInfo = (UriInfo) o;
        return Objects.equals(uri, uriInfo.uri)
                && Objects.equals(name, uriInfo.name)
                && Objects.equals(remark, uriInfo.remark)
                && Objects.equals(type, uriInfo.type)
                && Objects.equals(server, uriInfo.server);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, name, remark, type, server);
    }

    @Override
    public String toString() {
        return "{" +
                "uri='" + uri + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", type='" + type + '\'' +
                ", server='" + server + '\'' +
                '}';
    }
}
