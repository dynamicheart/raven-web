package com.dynamicheart.raven.repositories.user;

import com.dynamicheart.raven.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by dynamicheart on 21/8/2017.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findUserByUsername(String username);

}
