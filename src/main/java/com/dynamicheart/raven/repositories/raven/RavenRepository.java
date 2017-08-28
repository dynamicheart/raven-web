package com.dynamicheart.raven.repositories.raven;

import com.dynamicheart.raven.model.raven.Raven;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by dynamicheart on 21/8/2017.
 */
public interface RavenRepository extends MongoRepository<Raven, String>{
}
