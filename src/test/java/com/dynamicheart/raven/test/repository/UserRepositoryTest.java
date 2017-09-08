package com.dynamicheart.raven.test.repository;

import com.dynamicheart.raven.application.ApplicationConfiguration;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.repositories.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
public class UserRepositoryTest {
    @Inject
    UserRepository userRepository;

    @Test
    public void Test() {
        User user=new User();
        user.setUsername("julius_chen");
        userRepository.save(user);
        //System.out.println(userRepository.findUserByUsername("julius_chen"));
        System.out.println(userRepository.findAll());
    }
}
