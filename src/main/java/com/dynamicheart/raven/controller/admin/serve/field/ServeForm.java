package com.dynamicheart.raven.controller.admin.serve.field;

import com.dynamicheart.raven.model.serve.Serve;

public class ServeForm extends Serve {
    private String createdDateString;

    public String getCreatedDateString() {
        return createdDateString;
    }

    public void setCreatedDateString(String createdDateString) {
        this.createdDateString = createdDateString;
    }
}
