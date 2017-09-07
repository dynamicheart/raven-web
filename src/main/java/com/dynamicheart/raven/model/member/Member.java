package com.dynamicheart.raven.model.member;

import com.dynamicheart.raven.model.generic.RavenEntity;
import com.dynamicheart.raven.model.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class Member extends RavenEntity<String, Member>{
    @Id
    private String id;

    @Indexed
    @Field("house_id")
    private String houseId;

    @Indexed
    @DBRef
    @Field("user_id")
    private User user;

    @Field("role")
    private Integer role;

    @CreatedDate
    @Field("joining_date")
    private Date joiningDate;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Member && super.equals(object);
    }
}
