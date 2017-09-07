package com.dynamicheart.raven.repositories.whistleblowing;

import com.dynamicheart.raven.model.whistleblowing.WhistleBlowing;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by dynamicheart on 21/8/2017.
 */
public interface WhistleBlowingRepository extends MongoRepository<WhistleBlowing, String> {
}
