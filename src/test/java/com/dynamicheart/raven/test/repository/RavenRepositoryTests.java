package com.dynamicheart.raven.test.repository;

import static org.assertj.core.api.Assertions.*;

import com.dynamicheart.raven.application.ApplicationConfiguration;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.model.raven.optionpoll.OptionPoll;
import com.dynamicheart.raven.repositories.house.HouseRepository;
import com.dynamicheart.raven.repositories.raven.RavenRepository;
import com.dynamicheart.raven.repositories.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dynamicheart on 21/8/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
public class RavenRepositoryTests {

    @Inject
    RavenRepository repository;

    @Inject
    HouseRepository houseRepository;

    @Inject
    UserRepository userRepository;

    @Test
    public void saveTest() {
        Raven raven = new Raven();
        raven.setDescription("desc");
        raven.setAddresserId("59b256848007834ffc5b3978");
        raven.setTitle("title");
        raven.setHouseId("59b3de2a80078339a0209128");
        List<OptionPoll> lop = new ArrayList<OptionPoll>();
        OptionPoll op = new OptionPoll();
        op.setType(0);
        op.setQuestion("question");
        lop.add(op);
        OptionPoll op2 = new OptionPoll();
        op2.setType(1);
        op2.setOptions(new ArrayList<String>());
        op2.getOptions().add("a");
        op2.getOptions().add("b");
        lop.add(op2);
        raven.setOptionPolls(lop);
        repository.save(raven);
    }
}
