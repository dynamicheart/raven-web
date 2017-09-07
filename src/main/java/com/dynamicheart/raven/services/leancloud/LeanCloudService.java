package com.dynamicheart.raven.services.leancloud;

import com.dynamicheart.raven.model.leancloud.installation.LeanCloudInstallation;
import com.dynamicheart.raven.model.leancloud.push.LeanCloudPush;
import com.dynamicheart.raven.model.leancloud.push.LeanCloudPushData;
import com.dynamicheart.raven.model.leancloud.LeanCloudResponse;

public interface LeanCloudService {
    LeanCloudResponse push(LeanCloudPush push);

    LeanCloudResponse saveInstallation(LeanCloudInstallation installation);
}
