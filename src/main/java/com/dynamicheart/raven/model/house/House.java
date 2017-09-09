package com.dynamicheart.raven.model.house;

import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.model.generic.RavenEntity;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document
public class House extends RavenEntity<String,House>{
    @Id
    private String id;

    @Field("name")
    @Indexed
    private String name;

    @Field("status")
    private Integer status = Constants.HOUSE_STATUS_NORMAL;

    @Field("capacity")
    private Integer capacity = 100;

    @Field("member_numbers")
    private Integer memberNumbers = 0;

    @Field("sigil")
    private String sigil;

    @Field("description")
    private String description;

    @Field("publicity")
    private Boolean publicity = false;

    @Field("tags")
    private Set<String> tags = new HashSet<>();

    @CreatedDate
    @Field("created_at")
    private Date createdDate;

    @LastModifiedDate
    @Field("updated_at")
    private Date lastModifiedDate;

    @Indexed
    @Field("founder_id")
    private String founderId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getMemberNumbers() {
        return memberNumbers;
    }

    public void setMemberNumbers(Integer memberNumbers) {
        this.memberNumbers = memberNumbers;
    }

    public String getSigil() {
        return sigil;
    }

    public void setSigil(String sigil) {
        this.sigil = sigil;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Boolean getPublicity() {
        return publicity;
    }

    public void setPublicity(Boolean publicity) {
        this.publicity = publicity;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getFounderId() {
        return founderId;
    }

    public void setFounderId(String founderId) {
        this.founderId = founderId;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof House && super.equals(object);
    }
}
