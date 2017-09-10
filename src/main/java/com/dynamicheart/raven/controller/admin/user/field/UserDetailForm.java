package com.dynamicheart.raven.controller.admin.user.field;

import com.dynamicheart.raven.model.user.User;

public class UserDetailForm extends User {
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
