package com.dynamicheart.raven.controller.app.user.field;

import java.io.Serializable;

public class UpdateInstallationForm implements Serializable {

    private static final long serialVersionUID = 5190641710637613114L;

    private String installationId;

    public String getInstallationId() {
        return installationId;
    }

    public void setInstallationId(String installationId) {
        this.installationId = installationId;
    }
}
