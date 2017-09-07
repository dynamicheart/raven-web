package com.dynamicheart.raven.model.mold;

import com.dynamicheart.raven.model.generic.RavenEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Mold extends RavenEntity<String, Mold> {

    @Id
    private String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Mold && super.equals(object);
    }
}
