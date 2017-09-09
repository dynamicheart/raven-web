package com.dynamicheart.raven.leancloud.manager.redis;

import com.dynamicheart.raven.authorization.manager.TokenManager;
import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.leancloud.manager.InstallationManager;
import com.dynamicheart.raven.leancloud.model.installation.InstallationModel;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class RedisInstallationManager implements InstallationManager {

    @Inject
    private TokenManager tokenManager;

    @Inject
    private RedisTemplate<String,String> redis;

    @Override
    public InstallationModel get(String userId) {
        if(!tokenManager.isTokenAlive(userId)){
            redis.delete(String.format(Constants.REDIS_INSTALLATION_KEY_TEMPLATE, userId));
            return null;
        }

        String installationId = redis.boundValueOps(String.format(Constants.REDIS_INSTALLATION_KEY_TEMPLATE, userId)).get();
        if(installationId == null){
            return null;
        }

        return new InstallationModel(userId, installationId);
    }

    @Override
    public InstallationModel save(String userId, String installationId) {
        redis.boundValueOps(String.format(Constants.REDIS_INSTALLATION_KEY_TEMPLATE, userId)).set(installationId);;
        return new InstallationModel(userId, installationId);
    }
}
