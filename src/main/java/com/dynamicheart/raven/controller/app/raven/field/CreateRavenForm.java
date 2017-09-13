package com.dynamicheart.raven.controller.app.raven.field;

import com.dynamicheart.raven.model.raven.optionpoll.OptionPoll;

import java.util.List;

public class CreateRavenForm {

    private String houseId;

    private String addresserId;

    private List<String> addresseeIds;

    private String title;

    private String description;

    private Integer type;

    private List<OptionPoll> optionPolls;

    private String attachmentId;

    private Boolean mold;

    private String moldId;

    public Boolean getMold() {
        return mold;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getAddresserId() {
        return addresserId;
    }

    public void setAddresserId(String addresserId) {
        this.addresserId = addresserId;
    }

    public List<String> getAddresseeIds() {
        return addresseeIds;
    }

    public void setAddresseeIds(List<String> addresseeIds) {
        this.addresseeIds = addresseeIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<OptionPoll> getOptionPolls() {
        return optionPolls;
    }

    public void setOptionPolls(List<OptionPoll> optionPolls) {
        this.optionPolls = optionPolls;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Boolean isMold() {
        return mold;
    }

    public void setMold(Boolean mold) {
        this.mold = mold;
    }

    public String getMoldId() {
        return moldId;
    }

    public void setMoldId(String moldId) {
        this.moldId = moldId;
    }
}
