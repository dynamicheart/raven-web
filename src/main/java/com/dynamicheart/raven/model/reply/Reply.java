package com.dynamicheart.raven.model.reply;

import com.dynamicheart.raven.model.reply.Poll.Poll;
import com.dynamicheart.raven.model.generic.RavenEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
public class Reply extends RavenEntity<String, Reply>{

    private static final long serialVersionUID = 8466376061391031979L;

    @Id
    public String id;

    @Field("raven_id")
    private String ravenId;

    @Field("user_id")
    private String userId;

    @Field("polls")
    private List<Poll> polls;

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
}
