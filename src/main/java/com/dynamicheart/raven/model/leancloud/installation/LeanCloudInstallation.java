package com.dynamicheart.raven.model.leancloud.installation;

import java.util.HashSet;
import java.util.Set;

public class LeanCloudInstallation {
    private String deviceType;

    private String installationId;

    private String deviceToken;

    private Set<String> channels = new HashSet<>();
}
