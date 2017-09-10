package com.dynamicheart.raven.model.reply;

import com.dynamicheart.raven.model.reply.Poll.Poll;
import com.dynamicheart.raven.model.generic.RavenEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document
@CompoundIndexes(
        @CompoundIndex(name = "raven_user_idx", def = "{'raven_id':1, 'user_id':1}")
)
public class Reply extends RavenEntity<String, Reply> {

    @Id
    private String id;

    @Indexed
    @Field("raven_id")
    private String ravenId;

    @Field("user_id")
    private String userId;

    @Field("polls")
    private List<Poll> polls;

    @Field("content")
    private String content;

    @CreatedDate
    @Field("created_date")
    private Date createdDate;

    @LastModifiedDate
    @Field("updated_date")
    private Date updatedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRavenId() {
        return ravenId;
    }

    public void setRavenId(String ravenId) {
        this.ravenId = ravenId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Poll> getPolls() {
        return polls;
    }

    public void setPolls(List<Poll> polls) {
        this.polls = polls;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public boolean equals(Object object) {
        return object instanceof Reply && super.equals(object);
    }
}
