package com.dynamicheart.raven.repositories.raven;

import com.dynamicheart.raven.model.raven.Raven;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by dynamicheart on 21/8/2017.
 */
public interface RavenRepository extends MongoRepository<Raven, String> {

    List<Raven> findByAddresserId(String addresserId);

    @Query("{'addressee_ids' : ?0 }")
    List<Raven> findByAddresseeId(String addresseeId);

    @Query("{ $and: [ {'addressee_ids' : ?0 }, {'created_date': { '$gt': ?1 } } ] }")
    List<Raven> findByAddresseeIdAndCreatedDateAfter(String addresseeId, Date date);
}
