package com.dynamicheart.raven.authorization.manager.redis;

import com.dynamicheart.raven.authorization.manager.TokenManger;
import com.dynamicheart.raven.authorization.model.TokenModel;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by dynamicheart on 21/8/2017.
 */
@Component
public class RedisTokenManager implements TokenManger {

    private RedisTemplate<Long, String> redis;

    @Override
    public TokenModel create(Long userId) {
        return null;
    }

    @Override
    public boolean checkToken(TokenModel model) {
        return false;
    }

    @Override
    public TokenModel getToken(String authenticattion) {
        return null;
    }

    @Override
    public void deleteToken(Long userID) {

    }
}
