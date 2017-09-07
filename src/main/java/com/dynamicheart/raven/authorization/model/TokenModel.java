package com.dynamicheart.raven.authorization.model;

import com.dynamicheart.raven.constant.Constants;

import java.util.Base64;

/**
 * Created by dynamicheart on 21/8/2017.
 */
public class TokenModel {

    private String userId;

    private String token;

    public TokenModel(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String toBase64(){
        return String.format(Constants.AUTHORIZATION_TEMPLATE,
                Base64.getEncoder().encodeToString(userId.getBytes()),
                Base64.getEncoder().encodeToString(token.getBytes())
        );
    }

    public static TokenModel parseBase64(String authentication){
        if (authentication == null || authentication.length () == 0) {
            return null;
        }

        String [] param = authentication.split ("-");
        if (param.length != 2) {
            return null;
        }

        return new TokenModel(param[0], param[1]);
    }
}
