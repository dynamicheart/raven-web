package com.dynamicheart.raven.model.raven;

import com.dynamicheart.raven.model.generic.RavenEntity;
import com.dynamicheart.raven.model.raven.optionpoll.OptionPoll;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by dynamicheart on 21/8/2017.
 */
@Document
public class Raven extends RavenEntity<String, Raven> {
    @Id
    private String id;

    @Field("house_id")
    private String houseId;

    @Indexed
    @Field("addresser_id")
    private String addresserId;

    @Field("addressee_ids")
    private List<String> addresseeIds = new ArrayList<>();

    @Field("title")
    private String title;

    @Field("description")
    private String description;

    @Field("type")
    private Integer type;

    @Field("option_polls")
    private List<OptionPoll> optionPolls;

    @Field("attachment_id")
    private String attachmentId;

    @Field("mold")
    private Boolean mold = false;

    @Field("mold_id")
    private String moldId;

    @CreatedDate
    @Field("created_date")
    private Date createdDate;

    @LastModifiedDate
    @Field("updated_date")
    private Date updatedDate;

    public Boolean getMold() {
        return mold;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /*
    @Override
    public boolean equals(Object object) {
        return object instanceof Raven && super.equals(object);
    }
    */
}
