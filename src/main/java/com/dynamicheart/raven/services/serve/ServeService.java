package com.dynamicheart.raven.services.serve;

import com.dynamicheart.raven.model.serve.Serve;
import com.dynamicheart.raven.services.generic.RavenEntityService;

import java.util.List;

public interface ServeService extends RavenEntityService<String, Serve>{
    List<Serve> getAll();

    List<Serve> getAllHandlingToPublic();

    List<Serve> getAllByManId(String manId);

    List<Serve> getAllHandingOrdinaryByHouseId(String houseId);
}
