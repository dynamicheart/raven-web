package com.dynamicheart.raven.model.whistleblowing;

import com.dynamicheart.raven.model.generic.RavenEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Created by dynamicheart on 6/8/2017.
 */
@Document
public class WhistleBlowing extends RavenEntity<String, WhistleBlowing> {
    @Id
    private String id;

    @DBRef
    @Field("whistleblower_id")
    private String whistleblowerId;

    @DBRef
    @Field("raven_id")
    private String ravenId;

    @CreatedDate
    private Date createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWhistleblowerId() {
        return whistleblowerId;
    }

    public void setWhistleblowerId(String whistleblowerId) {
        this.whistleblowerId = whistleblowerId;
    }

    public String getRavenId() {
        return ravenId;
    }

    public void setRavenId(String ravenId) {
        this.ravenId = ravenId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof WhistleBlowing && super.equals(object);
    }
}
