package com.dynamicheart.raven.model.raven.optionpoll;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class OptionPoll {
    @Field("type")
    private Integer type;

    @Field("question")
    private String question;

    @Field("options")
    private List<String> options;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
