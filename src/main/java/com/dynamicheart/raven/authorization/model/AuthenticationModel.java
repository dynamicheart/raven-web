package com.dynamicheart.raven.authorization.model;

public class AuthenticationModel {

    private String userId;

    private String authentication;

    public AuthenticationModel(String userId, String authentication) {
        this.userId = userId;
        this.authentication = authentication;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
