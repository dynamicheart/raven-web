package com.dynamicheart.raven.repositories.serve;

import com.dynamicheart.raven.model.serve.Serve;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by dynamicheart on 21/8/2017.
 */
public interface ServeRepository extends MongoRepository<Serve, String> {

    List<Serve> findAllByStatusAndType(int status,int type);

    List<Serve> findAllByManId(String manId);

    List<Serve> findAllByStatusAndTypeAndHouseId(int status,int type,String houseId);
}
