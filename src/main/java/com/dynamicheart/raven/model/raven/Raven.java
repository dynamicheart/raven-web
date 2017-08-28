package com.dynamicheart.raven.model.raven;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * Created by dynamicheart on 21/8/2017.
 */
@Document
public class Raven {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
