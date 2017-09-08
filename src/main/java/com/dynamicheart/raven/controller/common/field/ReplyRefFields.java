package com.dynamicheart.raven.controller.common.field;

import com.dynamicheart.raven.model.reply.Poll.Poll;

import java.util.Date;
import java.util.List;

public class ReplyRefFields {
    private List<Poll> polls;

    private String content;

    private Date createdDate;

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
