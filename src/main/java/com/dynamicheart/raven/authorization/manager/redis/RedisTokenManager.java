package com.dynamicheart.raven.authorization.manager.redis;

import com.dynamicheart.raven.authorization.manager.TokenManager;
import com.dynamicheart.raven.authorization.model.TokenModel;
import com.dynamicheart.raven.constant.Constants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by dynamicheart on 21/8/2017.
 */
@Component
public class RedisTokenManager implements TokenManager {

    @Inject
    private RedisTemplate<String, String> redis;

    @Override
    public TokenModel createToken(String userId) {
        String token = UUID.randomUUID().toString();
        TokenModel model = new TokenModel(userId, token);
        redis.boundValueOps(String.format(Constants.REDIS_USER_KEY_TEMPLATE, userId)).set(token, Constants.TOKEN_EXPIRES_HOURS, TimeUnit.HOURS);
        return model;
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if(model == null) {
            return false;
        }
        String token = redis.boundValueOps(String.format(Constants.REDIS_USER_KEY_TEMPLATE, model.getUserId())).get();
        if (token == null || !token.equals(model.getToken())){
            return false;
        }
        //if checking succeed, prolong the token expire time
        redis.boundValueOps(String.format(Constants.REDIS_USER_KEY_TEMPLATE, model.getUserId())).expire(Constants.TOKEN_EXPIRES_HOURS, TimeUnit.HOURS);
        return true;
    }

    @Override
    public TokenModel getToken(String authentication) {
        return TokenModel.parseBase64(authentication);
    }

    @Override
    public void deleteToken(String userId) {
        redis.delete((String.format(Constants.REDIS_USER_KEY_TEMPLATE, userId)));
    }

    @Override
    public boolean isTokenAlive(String userId) {
        return redis.boundValueOps(String.format(Constants.REDIS_USER_KEY_TEMPLATE, userId)).get() != null;
    }

    @Override
    public TokenModel createAdminToken(String userId) {
        String token = UUID.randomUUID().toString();
        TokenModel model = new TokenModel(userId, token);
        redis.boundValueOps(String.format(Constants.REDIS_ADMIN_KEY_TEMPLATE, userId)).set(token, Constants.TOKEN_EXPIRES_HOURS, TimeUnit.HOURS);
        return model;
    }

    @Override
    public boolean checkAdminToken(TokenModel model) {
        if(model == null) {
            return false;
        }
        String token = redis.boundValueOps(String.format(Constants.REDIS_ADMIN_KEY_TEMPLATE, model.getUserId())).get();
        if (token == null || !token.equals(model.getToken())){
            return false;
        }
        //if checking succeed, prolong the token expire time
        redis.boundValueOps(String.format(Constants.REDIS_ADMIN_KEY_TEMPLATE, model.getUserId())).expire(Constants.TOKEN_EXPIRES_HOURS, TimeUnit.HOURS);
        return true;
    }

    @Override
    public TokenModel getAdminToken(String authentication) {
        return TokenModel.parseBase64(authentication);
    }

    @Override
    public void deleteAdminToken(String userId) {
        redis.delete((String.format(Constants.REDIS_ADMIN_KEY_TEMPLATE, userId)));
    }
}
