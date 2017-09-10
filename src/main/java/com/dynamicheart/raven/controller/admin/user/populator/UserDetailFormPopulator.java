package com.dynamicheart.raven.controller.admin.user.populator;

import com.dynamicheart.raven.controller.admin.user.field.UserDetailForm;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailFormPopulator extends AbstractDataPopulator<User,UserDetailForm>{
    @Override
    protected UserDetailForm createTarget() {
        return new UserDetailForm();
    }

    @Override
    public UserDetailForm populate(User user, UserDetailForm userDetailForm) throws ConversionException {
        userDetailForm.setAdmin(user.getAdmin());
        userDetailForm.setAvatar(user.getAvatar());
        userDetailForm.setCreatedDate(user.getCreatedDate());
        userDetailForm.setEmail(user.getEmail());
        userDetailForm.setId(user.getId());
        userDetailForm.setLastModifiedDate(user.getLastModifiedDate());
        userDetailForm.setPassword(user.getPassword());
        userDetailForm.setPhoneNumber(user.getPhoneNumber());
        userDetailForm.setStatus(user.getStatus());


        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        userDetailForm.setCreatedDateString(sdf.format(user.getCreatedDate()));
        userDetailForm.setUpdatedDateString(sdf.format(user.getLastModifiedDate()));

        return userDetailForm;
    }

    public List<UserDetailForm> populateList(List<User> userList) throws ConversionException {
        List<UserDetailForm> userDetailFormList=new ArrayList<>();
        for(User user:userList)
            userDetailFormList.add(populate(user,new UserDetailForm()));
        return userDetailFormList;
    }
}
