package com.dynamicheart.raven.controller.app.whistleblowing.field;

public class CreateWhistleBlowingForm {
    private String ravenId;
    private Integer type;
    private String description;

    public String getRavenId() {
        return ravenId;
    }

    public void setRavenId(String ravenId) {
        this.ravenId = ravenId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
