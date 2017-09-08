package com.dynamicheart.raven.leancloud.service;

import com.dynamicheart.raven.leancloud.model.installation.LeanCloudInstallation;
import com.dynamicheart.raven.leancloud.model.push.LeanCloudPush;
import com.dynamicheart.raven.leancloud.model.response.LeanCloudResponse;

public interface LeanCloudService {
    LeanCloudResponse push(LeanCloudPush push);

    LeanCloudResponse saveInstallationInRemote(LeanCloudInstallation installation);
}
