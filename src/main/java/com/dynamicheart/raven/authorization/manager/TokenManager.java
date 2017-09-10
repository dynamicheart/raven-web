package com.dynamicheart.raven.authorization.manager;

import com.dynamicheart.raven.authorization.model.TokenModel;

/**
 * Created by dynamicheart on 21/8/2017.
 */
public interface TokenManager {

    TokenModel createToken(String userId);

    boolean checkToken(TokenModel model);

    TokenModel getToken(String authentication);

    void deleteToken(String userId);

    boolean isTokenAlive(String userId);

    TokenModel createAdminToken(String userId);

    boolean checkAdminToken(TokenModel model);

    TokenModel getAdminToken(String authentication);

    void deleteAdminToken(String userId);
}
