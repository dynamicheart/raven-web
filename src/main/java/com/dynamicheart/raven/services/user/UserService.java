package com.dynamicheart.raven.services.user;

import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.generic.RavenEntityService;

import java.util.List;

public interface UserService extends RavenEntityService<String, User>{
    Boolean exists(List<String> userIds);
}
