package com.dynamicheart.raven.model.raven;

import com.dynamicheart.raven.model.generic.RavenEntity;
import com.dynamicheart.raven.model.raven.optionpoll.OptionPoll;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dynamicheart on 21/8/2017.
 */
@Document
public class Raven extends RavenEntity<String, Raven>{
    @Id
    private String id;

    @Field("house_id")
    private String houseId;

    @Field("addresser_id")
    private String addresserId;

    @Field("addressees_ids")
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

    @Override
    public boolean equals(Object object) {
        return object instanceof Raven && super.equals(object);
    }
}
