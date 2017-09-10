package com.dynamicheart.raven.leancloud.model.installation;

public class InstallationModel {

    private String userId;

    private String installationId;

    public InstallationModel(String userId, String installationId) {
        this.userId = userId;
        this.installationId = installationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInstallationId() {
        return installationId;
    }

    public void setInstallationId(String installationId) {
        this.installationId = installationId;
    }
}
