package com.dynamicheart.raven.controller.app.reply.populator;

import com.dynamicheart.raven.controller.app.reply.field.CreateReplyForm;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.reply.Reply;
import com.dynamicheart.raven.utils.exception.ConversionException;

public class CreateReplyFormPopulator extends AbstractDataPopulator<CreateReplyForm,Reply>{
    @Override
    protected Reply createTarget() {
        return new Reply();
    }

    @Override
    public Reply populate(CreateReplyForm createReplyForm, Reply reply) throws ConversionException {
        reply.setContent(createReplyForm.getContent());
        reply.setPolls(createReplyForm.getPolls());
        reply.setRavenId(createReplyForm.getRavenId());
        reply.setUserId(createReplyForm.getUserId());
        return reply;
    }
}
