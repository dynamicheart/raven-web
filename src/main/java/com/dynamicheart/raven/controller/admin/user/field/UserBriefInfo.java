package com.dynamicheart.raven.controller.admin.user.field;

import java.io.Serializable;

public class UserBriefInfo implements Serializable {

    private String id;

    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
