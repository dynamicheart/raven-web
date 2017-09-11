package com.dynamicheart.raven.controller.app.token.field;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
    @NotEmpty(message =  "用户名不能为空")
    private String username;

    @NotEmpty(message = "用户名不能为空")
    private String password;

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
}
