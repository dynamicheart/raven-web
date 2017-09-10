package com.dynamicheart.raven.controller.app.raven.field;

import com.dynamicheart.raven.controller.common.field.HouseRefFields;
import com.dynamicheart.raven.model.raven.optionpoll.OptionPoll;

import java.util.Date;
import java.util.List;

public class RavenInfoFields {
    private String id;

    private HouseRefFields house;

    private String title;

    private String description;

    private Integer type;

    private List<OptionPoll> optionPolls;

    private String attachmentId;

    private Boolean mold = false;

    private String moldId;

    private Date createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HouseRefFields getHouse() {
        return house;
    }

    public void setHouse(HouseRefFields house) {
        this.house = house;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
