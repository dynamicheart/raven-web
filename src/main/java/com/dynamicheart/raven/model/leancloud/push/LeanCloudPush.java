package com.dynamicheart.raven.model.leancloud.push;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LeanCloudPush implements Serializable{

    private static final long serialVersionUID = 1699941338653415619L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("where")
    private String where;

    @JsonProperty("data")
    private LeanCloudPushData data;

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public LeanCloudPushData getData() {
        return data;
    }

    public void setData(LeanCloudPushData data) {
        this.data = data;
    }
}
