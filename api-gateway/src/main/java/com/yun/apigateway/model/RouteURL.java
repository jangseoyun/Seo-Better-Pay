package com.yun.apigateway.model;
public enum RouteURL {
    HTTP("http://"),
    HTTPS("https://");

    private String protocol;

    RouteURL(String protocol) {
        this.protocol = protocol;
    }
}
