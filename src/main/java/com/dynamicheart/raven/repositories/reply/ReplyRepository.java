package com.dynamicheart.raven.repositories.reply;

import com.dynamicheart.raven.model.reply.Reply;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReplyRepository extends MongoRepository<Reply, String> {

    List<Reply> findByRavenId(String ravenId);

    Reply findTopByRavenIdAndUserId(String ravenId, String userId);
}
