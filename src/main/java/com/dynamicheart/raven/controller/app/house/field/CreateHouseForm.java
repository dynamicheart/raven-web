package com.dynamicheart.raven.controller.app.house.field;

import java.util.Set;

public class CreateHouseForm {

    private String name;

    private String description;

    private Set<String> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
