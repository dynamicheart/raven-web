package com.dynamicheart.raven.leancloud.manager;

import com.dynamicheart.raven.leancloud.model.installation.InstallationModel;

public interface InstallationManager{
    InstallationModel get(String userId);

    InstallationModel save(String userId, String installationId);
}
