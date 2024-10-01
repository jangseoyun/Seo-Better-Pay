package com.yun.apigateway.model;

public enum HeaderInfo {
    PRE_REQUEST_HEADER("pre-haeder", "pre-request-header");

    private String name;
    private String value;

    HeaderInfo(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
