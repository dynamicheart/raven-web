package com.dynamicheart.raven.controller.app.mold.populator;

import com.dynamicheart.raven.controller.app.mold.field.MoldInfoFields;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.controller.common.field.UserRefFields;
import com.dynamicheart.raven.model.mold.Mold;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.user.UserService;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class MoldInfoFieldsPopulator extends AbstractDataPopulator<Mold,MoldInfoFields>{
    @Inject
    private UserService userService;

    @Override
    protected MoldInfoFields createTarget() {
        return new MoldInfoFields();
    }

    @Override
    public MoldInfoFields populate(Mold mold, MoldInfoFields moldInfoFields) throws ConversionException {
        moldInfoFields.setContent(mold.getContent());
        moldInfoFields.setCreatedDate(mold.getCreatedDate());
        moldInfoFields.setId(mold.getId());
        moldInfoFields.setTags(mold.getTags());
        moldInfoFields.setUpdatedDate(mold.getUpdatedDate());
        moldInfoFields.setUsageCount(String.valueOf(mold.getUsageCount()));

        UserRefFields userRefFields=new UserRefFields();
        userRefFields.setId(mold.getAuthorId());

        User user=userService.getById(mold.getAuthorId());
        if(user!=null)
            userRefFields.setUsername(user.getUsername());

        moldInfoFields.setUser(userRefFields);
        return moldInfoFields;
    }
}
