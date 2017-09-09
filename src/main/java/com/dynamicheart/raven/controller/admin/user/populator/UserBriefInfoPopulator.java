package com.dynamicheart.raven.controller.admin.user.populator;

import com.dynamicheart.raven.controller.admin.user.field.UserBriefInfo;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserBriefInfoPopulator extends AbstractDataPopulator<User, UserBriefInfo> {

    @Override
    protected UserBriefInfo createTarget() {
        return new UserBriefInfo();
    }

    @Override
    public UserBriefInfo populate(User user, UserBriefInfo userBriefInfo) throws ConversionException {
        userBriefInfo.setId(user.getId());
        userBriefInfo.setUsername(user.getUsername());
        return userBriefInfo;
    }

    public List<UserBriefInfo> populateList(List<User> userList) throws ConversionException{
        List<UserBriefInfo> userBriefInfoList=new ArrayList<>();
        for(User user:userList)
            userBriefInfoList.add(populate(user,new UserBriefInfo()));
        return userBriefInfoList;
    }
}
