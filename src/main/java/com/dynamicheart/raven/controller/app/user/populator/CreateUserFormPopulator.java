package com.dynamicheart.raven.controller.app.user.populator;

import com.dynamicheart.raven.controller.app.user.field.CreateUserForm;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class CreateUserFormPopulator extends AbstractDataPopulator<CreateUserForm, User> {

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    protected User createTarget() {
        return new User();
    }

    @Override
    public User populate(CreateUserForm createUserForm, User user) throws ConversionException {
        user.setUsername(createUserForm.getUsername());
        user.setPassword(passwordEncoder.encode(createUserForm.getPassword()));
        user.setEmail(createUserForm.getEmail());
        user.setPhoneNumber(createUserForm.getPhoneNumber());
        return user;
    }
}
