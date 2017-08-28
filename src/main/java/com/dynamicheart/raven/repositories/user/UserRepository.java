package com.dynamicheart.raven.repositories.user;

import com.dynamicheart.raven.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dynamicheart on 21/8/2017.
 */

public interface UserRepository extends JpaRepository<User, Long> {
}
