package com.dynamicheart.raven.services.reply;

import com.dynamicheart.raven.model.reply.Reply;
import com.dynamicheart.raven.services.generic.RavenEntityService;

import java.util.List;

public interface ReplyService extends RavenEntityService<String, Reply>{

    List<Reply> findByRavenId(String ravenId);

    Reply findTopByRavenIdAndUserId(String ravenId, String userId);
}
