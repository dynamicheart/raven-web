package com.dynamicheart.raven.leancloud.model.push.component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PushModelData implements Serializable{

    private static final long serialVersionUID = 4989733451384168740L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String alert;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String action;

    @JsonProperty("custom-key")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customKey;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean silent;

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCustomKey() {
        return customKey;
    }

    public void setCustomKey(String customKey) {
        this.customKey = customKey;
    }

    public Boolean getSilent() {
        return silent;
    }

    public void setSilent(Boolean silent) {
        this.silent = silent;
    }
}
