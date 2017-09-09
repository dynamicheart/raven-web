package com.dynamicheart.raven.controller.app.house.field;

import com.dynamicheart.raven.controller.common.field.MemberRefFields;
import com.dynamicheart.raven.controller.common.field.UserRefFields;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class HouseInfoFields {

    private String id;

    private String name;

    private Integer status;

    private Integer capacity;

    private Integer memberNumbers;

    private String sigil;

    private String description;

    private Boolean publicity;

    private Set<String> tags;

    private Date createdDate;

    private UserRefFields founder;

    private List<MemberRefFields> members;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSigil() {
        return sigil;
    }

    public void setSigil(String sigil) {
        this.sigil = sigil;
    }

    public Boolean getPublicity() {
        return publicity;
    }

    public void setPublicity(Boolean publicity) {
        this.publicity = publicity;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserRefFields getFounder() {
        return founder;
    }

    public void setFounder(UserRefFields founder) {
        this.founder = founder;
    }

    public List<MemberRefFields> getMembers() {
        return members;
    }

    public void setMembers(List<MemberRefFields> members) {
        this.members = members;
    }
}
