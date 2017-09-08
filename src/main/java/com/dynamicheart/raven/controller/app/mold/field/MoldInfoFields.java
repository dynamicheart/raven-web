package com.dynamicheart.raven.controller.app.mold.field;

import com.dynamicheart.raven.controller.common.field.UserRefFields;

import java.util.Date;
import java.util.Set;

public class MoldInfoFields {

    private String id;

    private String content;

    private String usageCount;

    private Set<String> tags;

    private Date createdDate;

    private Date updatedDate;

    private UserRefFields user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(String usageCount) {
        this.usageCount = usageCount;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UserRefFields getUser() {
        return user;
    }

    public void setUser(UserRefFields user) {
        this.user = user;
    }
}
