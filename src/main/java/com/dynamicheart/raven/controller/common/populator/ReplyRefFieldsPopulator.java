package com.dynamicheart.raven.controller.common.populator;

import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.controller.common.field.ReplyRefFields;
import com.dynamicheart.raven.model.reply.Reply;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class ReplyRefFieldsPopulator extends AbstractDataPopulator<Reply, ReplyRefFields> {
    @Override
    protected ReplyRefFields createTarget() {
        return new ReplyRefFields();
    }

    @Override
    public ReplyRefFields populate(@NotNull Reply reply,@NotNull ReplyRefFields replyRefFields) throws ConversionException {
        replyRefFields.setPolls(reply.getPolls());
        replyRefFields.setContent(reply.getContent());
        replyRefFields.setCreatedDate(reply.getCreatedDate());
        return replyRefFields;
    }
}
