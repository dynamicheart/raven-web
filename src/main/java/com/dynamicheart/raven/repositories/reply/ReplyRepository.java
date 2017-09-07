package com.dynamicheart.raven.repositories.reply;

import com.dynamicheart.raven.model.reply.Reply;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReplyRepository extends MongoRepository<Reply, String> {
}
