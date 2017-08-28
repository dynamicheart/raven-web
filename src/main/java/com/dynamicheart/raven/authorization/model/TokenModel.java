package com.dynamicheart.raven.authorization.model;

/**
 * Created by dynamicheart on 21/8/2017.
 */
public class TokenModel {

    private Long userId;

    private String token;

    public TokenModel(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
