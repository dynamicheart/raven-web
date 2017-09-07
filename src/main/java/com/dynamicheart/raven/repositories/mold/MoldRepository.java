package com.dynamicheart.raven.repositories.mold;

import com.dynamicheart.raven.model.mold.Mold;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MoldRepository extends MongoRepository<Mold, String> {
}
