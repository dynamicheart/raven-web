package com.dynamicheart.raven.test.util;

import com.dynamicheart.raven.application.ApplicationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
public class EncoderTest {
    @Inject
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncoder(){
        Assert.isTrue(passwordEncoder.matches("123","$2a$11$T4N8d35pPYgJc5ULkrq2T.bi4N.atpeQdYtHSdDKzN/DhaNlpM7vy"));

    }
}
