package com.dynamicheart.raven.leancloud.service;

import com.dynamicheart.raven.leancloud.model.push.PushModel;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.utils.exception.ServiceException;

public interface LeanCloudService {
    void send(Raven raven, User addresser) throws ServiceException;
}
