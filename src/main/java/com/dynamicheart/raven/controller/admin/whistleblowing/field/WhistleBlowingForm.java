package com.dynamicheart.raven.controller.admin.whistleblowing.field;

import com.dynamicheart.raven.model.whistleblowing.WhistleBlowing;

public class WhistleBlowingForm extends WhistleBlowing {
    private String CreatedDateString;

    public String getCreatedDateString() {
        return CreatedDateString;
    }

    public void setCreatedDateString(String createdDateString) {
        CreatedDateString = createdDateString;
    }
}
