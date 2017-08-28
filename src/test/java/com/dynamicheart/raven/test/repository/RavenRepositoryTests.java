package com.dynamicheart.raven.test.repository;

import static org.assertj.core.api.Assertions.*;

import com.dynamicheart.raven.application.ApplicationConfiguration;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.repositories.raven.RavenRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

/**
 * Created by dynamicheart on 21/8/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
public class RavenRepositoryTests {

    @Inject
    RavenRepository repository;

    @Test
    public void saveTest() {
        Raven raven = repository.save(new Raven());
        assertThat(raven.getId()).isNotNull();
    }

    @Test
    public void a(){
        assertThat(1).isNotZero();
    }
}
