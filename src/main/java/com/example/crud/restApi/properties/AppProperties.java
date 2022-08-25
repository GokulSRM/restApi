package com.example.crud.restApi.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties()
public class AppProperties {
    private String baseUrl = "/api";

    private String getAllUrl = "/students";

    private String getUrl = "/student/{id}";

    private String postUrl = "/student";

    private String putUrl = "/student/{id}";

    private String deleteUrl = "/student/{id}";

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getGetAllUrl() {
        return getAllUrl;
    }

    public void setGetAllUrl(String getAllUrl) {
        this.getAllUrl = getAllUrl;
    }

    public String getGetUrl() {
        return getUrl;
    }

    public void setGetUrl(String getUrl) {
        this.getUrl = getUrl;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getPutUrl() {
        return putUrl;
    }

    public void setPutUrl(String putUrl) {
        this.putUrl = putUrl;
    }

    public String getDeleteUrl() {
        return deleteUrl;
    }

    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }

    
}
