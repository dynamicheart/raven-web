package com.dynamicheart.raven.controller.app.user.populator;

import com.dynamicheart.raven.controller.app.user.field.UpdateUserForm;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserFormPopulator extends AbstractDataPopulator<UpdateUserForm, User>{
    @Override
    protected User createTarget() {
        return new User();
    }

    @Override
    public User populate(UpdateUserForm updateUserForm, User user) throws ConversionException {
        if(updateUserForm.getUsername() != null){
            user.setUsername(updateUserForm.getUsername());
        }

        if(updateUserForm.getEmail() != null){
            user.setEmail(updateUserForm.getPhoneNumber());
        }

        if(updateUserForm.getPhoneNumber() != null){
            user.setPhoneNumber(updateUserForm.getPhoneNumber());
        }
        return user;
    }
}
