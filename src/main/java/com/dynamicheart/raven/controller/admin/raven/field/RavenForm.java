package com.dynamicheart.raven.controller.admin.raven.field;

import com.dynamicheart.raven.model.raven.Raven;

public class RavenForm extends Raven {

    private String createdDateString;

    private String updatedDateString;

    public String getCreatedDateString() {
        return createdDateString;
    }

    public void setCreatedDateString(String createdDateString) {
        this.createdDateString = createdDateString;
    }

    public String getUpdatedDateString() {
        return updatedDateString;
    }

    public void setUpdatedDateString(String updatedDateString) {
        this.updatedDateString = updatedDateString;
    }
}
