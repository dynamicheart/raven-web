package com.dynamicheart.raven.test.repository;

import com.dynamicheart.raven.application.ApplicationConfiguration;
import com.dynamicheart.raven.model.serve.Serve;
import com.dynamicheart.raven.model.whistleblowing.WhistleBlowing;
import com.dynamicheart.raven.repositories.serve.ServeRepository;
import com.dynamicheart.raven.repositories.whistleblowing.WhistleBlowingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
public class ServeRepositoryTest {

    @Inject
    ServeRepository repository;

    @Test
    public void saveTest() {
        Serve serve = new Serve();
        repository.save(serve);
    }
}