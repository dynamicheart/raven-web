package com.dynamicheart.raven.repositories.house;

import com.dynamicheart.raven.model.house.House;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by dynamicheart on 21/8/2017.
 */
public interface HouseRepository extends MongoRepository<House, String> {
    List<House> findHousesByNameLike(String name);

    House findHouseByName(String name);
}
