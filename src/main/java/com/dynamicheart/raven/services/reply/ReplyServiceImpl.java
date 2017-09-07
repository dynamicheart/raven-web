package com.dynamicheart.raven.services.reply;

import com.dynamicheart.raven.model.reply.Reply;
import com.dynamicheart.raven.repositories.reply.ReplyRepository;
import com.dynamicheart.raven.services.generic.RavenEntityServiceImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class ReplyServiceImpl extends RavenEntityServiceImpl<String, Reply>
        implements ReplyService {

    private ReplyRepository replyRepository;

    @Inject
    public ReplyServiceImpl(ReplyRepository replyRepository) {
        super(replyRepository);
        this.replyRepository = replyRepository;
    }
}
