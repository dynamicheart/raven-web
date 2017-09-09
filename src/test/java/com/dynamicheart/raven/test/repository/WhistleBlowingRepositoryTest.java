package com.dynamicheart.raven.test.repository;

import com.dynamicheart.raven.application.ApplicationConfiguration;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.model.whistleblowing.WhistleBlowing;
import com.dynamicheart.raven.repositories.whistleblowing.WhistleBlowingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
public class WhistleBlowingRepositoryTest {

    @Inject
    WhistleBlowingRepository repository;

    @Test
    public void saveTest() {
        WhistleBlowing whistleBlowing = new WhistleBlowing();
        whistleBlowing.setRavenId("59b3f2368007831104883b45");
        whistleBlowing.setWhistleblowerId("59b256848007834ffc5b3978");
        whistleBlowing.setCreatedDate(new Date());
        whistleBlowing.setStatus(0);
        repository.save(whistleBlowing);
    }
}