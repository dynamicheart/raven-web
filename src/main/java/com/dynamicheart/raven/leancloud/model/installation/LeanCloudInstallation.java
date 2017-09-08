package com.dynamicheart.raven.leancloud.model.installation;

import java.util.HashSet;
import java.util.Set;

public class LeanCloudInstallation {
    private String deviceType;

    private String installationId;

    private String deviceToken;

    private Set<String> channels = new HashSet<>();
}
