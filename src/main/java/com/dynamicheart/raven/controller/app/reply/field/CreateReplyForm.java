package com.dynamicheart.raven.controller.app.reply.field;

import com.dynamicheart.raven.model.reply.Poll.Poll;

import java.util.List;

public class CreateReplyForm {

    private String ravenId;

    private String userId;

    private List<Poll> polls;

    private String content;

    public String getRavenId() {
        return ravenId;
    }

    public void setRavenId(String ravenId) {
        this.ravenId = ravenId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
