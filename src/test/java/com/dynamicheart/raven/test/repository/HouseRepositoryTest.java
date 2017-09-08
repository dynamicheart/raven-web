package com.dynamicheart.raven.test.repository;

import com.dynamicheart.raven.application.ApplicationConfiguration;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.repositories.house.HouseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
public class HouseRepositoryTest {
    @Inject
    HouseRepository houseRepository;

    @Test
    public void Test() {
        House house=new House();
        String name="myGroup";
        house.setName(name);
        house.setCapacity(50);
        houseRepository.save(house);
        System.out.println(houseRepository.findAll());
    }
}
