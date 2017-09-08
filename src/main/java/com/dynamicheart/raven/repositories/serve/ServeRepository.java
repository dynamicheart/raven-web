package com.dynamicheart.raven.repositories.serve;

import com.dynamicheart.raven.model.serve.Serve;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by dynamicheart on 21/8/2017.
 */
public interface ServeRepository extends MongoRepository<Serve, String> {
}
