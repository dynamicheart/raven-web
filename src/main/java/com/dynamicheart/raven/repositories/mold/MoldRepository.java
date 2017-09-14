package com.dynamicheart.raven.repositories.mold;

import com.dynamicheart.raven.model.mold.Mold;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MoldRepository extends MongoRepository<Mold, String> {
    List<Mold> findAllByTagsContains(String tag);
}
