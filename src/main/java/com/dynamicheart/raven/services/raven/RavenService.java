package com.dynamicheart.raven.services.raven;

import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.services.generic.RavenEntityService;

import java.util.Date;
import java.util.List;

public interface RavenService extends RavenEntityService<String, Raven> {
    List<Raven> findByAddresserId(String addresserId);

    List<Raven> findByAddresseeId(String addresseeId);

    List<Raven> findByAddresseeIdAndCreatedDateAfter(String addresseeId, Date date);
}
