package com.dynamicheart.raven.services.reply;

import com.dynamicheart.raven.model.reply.Reply;
import com.dynamicheart.raven.repositories.reply.ReplyRepository;
import com.dynamicheart.raven.services.generic.RavenEntityServiceImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ReplyServiceImpl extends RavenEntityServiceImpl<String, Reply>
        implements ReplyService {

    private ReplyRepository replyRepository;

    @Inject
    public ReplyServiceImpl(ReplyRepository replyRepository) {
        super(replyRepository);
        this.replyRepository = replyRepository;
    }

    @Override
    public List<Reply> findByRavenId(String ravenId) {
        return replyRepository.findByRavenId(ravenId);
    }

    @Override
    public Reply findTopByRavenIdAndUserId(String ravenId, String userId) {
        return replyRepository.findTopByRavenIdAndUserId(ravenId, userId);
    }
}
