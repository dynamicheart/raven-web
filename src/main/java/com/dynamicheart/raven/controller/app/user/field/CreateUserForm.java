package com.dynamicheart.raven.controller.app.user.field;

import java.io.Serializable;

public class CreateUserForm implements Serializable {

    private static final long serialVersionUID = -803068377918657270L;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
