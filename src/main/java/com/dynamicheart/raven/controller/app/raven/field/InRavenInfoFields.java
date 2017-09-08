package com.dynamicheart.raven.controller.app.raven.field;

import com.dynamicheart.raven.controller.common.field.HouseRefFields;
import com.dynamicheart.raven.controller.common.field.ReplyRefFields;
import com.dynamicheart.raven.controller.common.field.UserRefFields;
import com.dynamicheart.raven.model.raven.optionpoll.OptionPoll;

import java.util.Date;
import java.util.List;

public class InRavenInfoFields {

    private String id;

    private HouseRefFields house;

    private UserRefFields addresser;

    private String title;

    private String description;

    private Integer type;

    private List<OptionPoll> optionPolls;

    private String attachmentId;

    private ReplyRefFields replyRefFields;

    private Boolean mold = false;

    private String moldContent;

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

    public UserRefFields getAddresser() {
        return addresser;
    }

    public void setAddresser(UserRefFields addresser) {
        this.addresser = addresser;
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

    public ReplyRefFields getReplyRefFields() {
        return replyRefFields;
    }

    public void setReplyRefFields(ReplyRefFields replyRefFields) {
        this.replyRefFields = replyRefFields;
    }

    public Boolean isMold() {
        return mold;
    }

    public void setMold(Boolean mold) {
        this.mold = mold;
    }

    public String getMoldContent() {
        return moldContent;
    }

    public void setMoldContent(String moldContent) {
        this.moldContent = moldContent;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
