package com.dynamicheart.raven.authorization.manager;

import com.dynamicheart.raven.authorization.model.TokenModel;

/**
 * Created by dynamicheart on 21/8/2017.
 */
public interface TokenManger {

    TokenModel create(Long userId);

    boolean checkToken(TokenModel model);

    TokenModel getToken(String authenticattion);

    void deleteToken(Long userID);
}
