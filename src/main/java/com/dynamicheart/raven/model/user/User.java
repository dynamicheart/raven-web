package com.dynamicheart.raven.model.user;

import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.model.generic.RavenEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by dynamicheart on 6/8/2017.
 */
@Document
public class User extends RavenEntity<String, User>{
    @Id
    private String id;

    @Indexed(unique = true)
    @Field("username")
    private String username;

    @Field("password")
    private String password;

    @Indexed(unique = true)
    @Field("email")
    private String email;

    @Indexed(unique = true)
    @Field("phone_number")
    private String phoneNumber;

    @Field("status")
    private Integer status = Constants.USER_STATUS_OK;

    @Field("admin")
    private Boolean admin = false;

    @Field("avatar")
    private String avatar;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof User && super.equals(object);
    }
}
