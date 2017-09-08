package com.dynamicheart.raven.services.house;

import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.generic.RavenEntityService;
import com.dynamicheart.raven.utils.exception.ServiceException;

import java.util.List;

public interface HouseService extends RavenEntityService<String, House> {

    House create(House house, User founder) throws ServiceException;

    List<House> getAll();

    List<House> getBySimilarName(String name);

    House getByName(String name);
}
