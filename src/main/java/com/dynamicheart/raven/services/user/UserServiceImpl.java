package com.dynamicheart.raven.services.user;

import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.repositories.user.UserRepository;
import com.dynamicheart.raven.services.generic.RavenEntityServiceImpl;
import com.dynamicheart.raven.utils.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("userService")
public class UserServiceImpl extends RavenEntityServiceImpl<String, User>
        implements UserService {

    private UserRepository userRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }
}
