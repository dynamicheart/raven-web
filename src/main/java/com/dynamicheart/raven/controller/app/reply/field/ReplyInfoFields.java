package com.dynamicheart.raven.controller.app.reply.field;

import com.dynamicheart.raven.controller.common.field.UserRefFields;
import com.dynamicheart.raven.model.reply.Poll.Poll;

import java.util.Date;
import java.util.List;

public class ReplyInfoFields {

    private String id;

    private String ravenId;

    private UserRefFields user;

    private List<Poll> polls;

    private String content;

    private Date createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRavenId() {
        return ravenId;
    }

    public void setRavenId(String ravenId) {
        this.ravenId = ravenId;
    }

    public UserRefFields getUser() {
        return user;
    }

    public void setUser(UserRefFields user) {
        this.user = user;
    }

    public List<Poll> getPolls() {
        return polls;
    }

    public void setPolls(List<Poll> polls) {
        this.polls = polls;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
