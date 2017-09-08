package com.dynamicheart.raven.leancloud.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeanCloudResponse implements Serializable{

    private static final long serialVersionUID = 6594333023476998871L;

    @JsonProperty("code")
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
