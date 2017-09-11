package com.dynamicheart.raven.test.model;

import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.repositories.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/application-context.xml"})
public class SaveUserTest {
    @Resource
    UserRepository userRepository;

    @Test
    public void test(){
        User user=new User();
        user.setUsername("julius_chen");
        user.setPassword("666666");
        userRepository.save(user);
        System.out.println(userRepository.findTopByUsername("julius_chen"));
    }
}