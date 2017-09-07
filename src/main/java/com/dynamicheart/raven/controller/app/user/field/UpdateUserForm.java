package com.dynamicheart.raven.controller.app.user.field;

import java.io.Serializable;

public class UpdateUserForm implements Serializable {

    private static final long serialVersionUID = 2260908152139022787L;

    private String username;

    private String email;

    private String phoneNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
