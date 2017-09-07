package com.dynamicheart.raven.controller.common.populator;

import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.controller.common.field.UserRefFields;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class UserRefFieldsPopulator extends AbstractDataPopulator<User, UserRefFields> {

    @Override
    protected UserRefFields createTarget() {
        return new UserRefFields();
    }


    @Override
    public UserRefFields populate(@NotNull User user, @NotNull UserRefFields userRefFields) throws ConversionException {
        userRefFields.setId(user.getId());
        userRefFields.setUsername(user.getUsername());
        return userRefFields;
    }
}
