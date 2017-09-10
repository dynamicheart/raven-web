package com.dynamicheart.raven.controller.admin.house.field;

import com.dynamicheart.raven.model.house.House;

public class HouseDetailForm extends House {
    private String master;

    private String createdDateString;

    private String updatedDateString;

    public String getCreatedDateString() {
        return createdDateString;
    }

    public void setCreatedDateString(String createdDateString) {
        this.createdDateString = createdDateString;
    }

    public String getUpdatedDateString() {
        return updatedDateString;
    }

    public void setUpdatedDateString(String updatedDateString) {
        this.updatedDateString = updatedDateString;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

}
