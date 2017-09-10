package com.dynamicheart.raven.model.serve;

import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.model.generic.RavenEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Created by dynamicheart on 6/8/2017.
 */
@Document
public class Serve extends RavenEntity<String, Serve>{
    @Id
    private String id;

    @Indexed
    @Field("house_id")
    private String houseId;

    @Indexed
    @Field("man_id")
    private String manId;

    @Field("content")
    private String content;

    @Field("status")
    private Integer status= Constants.SERVE_STATUS_HANDLING;

    @Field("type")
    private Integer type=Constants.SERVE_TYPE_ORDINARY;

    @CreatedDate
    @Field("created_date")
    private Date createdDate;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getManId() {
        return manId;
    }

    public void setManId(String manId) {
        this.manId = manId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Serve && super.equals(object);
    }
}
