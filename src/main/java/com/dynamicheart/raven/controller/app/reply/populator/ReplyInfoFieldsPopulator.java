package com.dynamicheart.raven.controller.app.reply.populator;

import com.dynamicheart.raven.controller.app.reply.field.ReplyInfoFields;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.controller.common.populator.UserRefFieldsPopulator;
import com.dynamicheart.raven.model.reply.Reply;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.user.UserService;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

@Component
public class ReplyInfoFieldsPopulator extends AbstractDataPopulator<Reply, ReplyInfoFields>{

    @Inject
    private UserRefFieldsPopulator userRefFieldsPopulator;

    @Inject
    private UserService userService;

    @Override
    protected ReplyInfoFields createTarget() {
        return new ReplyInfoFields();
    }

    @Override
    public ReplyInfoFields populate(@NotNull Reply reply, @NotNull ReplyInfoFields replyInfoFields) throws ConversionException {
        replyInfoFields.setId(reply.getId());
        replyInfoFields.setRavenId(reply.getRavenId());
        replyInfoFields.setPolls(reply.getPolls());
        replyInfoFields.setContent(reply.getContent());
        replyInfoFields.setCreatedDate(reply.getCreatedDate());

        User user = userService.getById(reply.getUserId());
        if(user != null){
            replyInfoFields.setUser(userRefFieldsPopulator.populate(user));
        }
        return replyInfoFields;
    }
}
