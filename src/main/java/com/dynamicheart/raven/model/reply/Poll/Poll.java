package com.dynamicheart.raven.model.reply.Poll;

import org.springframework.data.mongodb.core.mapping.Field;

public class Poll {
    @Field("type")
    private Integer type;

    @Field("selection")
    private Integer selection;

    @Field("content")
    private String content;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSelection() {
        return selection;
    }

    public void setSelection(Integer selection) {
        this.selection = selection;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
