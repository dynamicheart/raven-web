package com.dynamicheart.raven.controller.app.user.populator;

import com.dynamicheart.raven.controller.app.user.field.UserInfoFields;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class UserInfoFieldsPopulator extends AbstractDataPopulator<User, UserInfoFields> {
    @Override
    protected UserInfoFields createTarget() {
        return new UserInfoFields();
    }

    @Override
    public UserInfoFields populate(@NotNull User user, UserInfoFields userInfoFields) throws ConversionException {
        userInfoFields.setId(user.getId());
        userInfoFields.setUsername(user.getUsername());
        userInfoFields.setEmail(user.getEmail());
        userInfoFields.setPhoneNumber(user.getPhoneNumber());
        userInfoFields.setStatus(user.getStatus());
        userInfoFields.setAvatar(user.getAvatar());
        return userInfoFields;
    }
}
