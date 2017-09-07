package com.dynamicheart.raven.services.house;

import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.repositories.house.HouseRepository;
import com.dynamicheart.raven.services.generic.RavenEntityServiceImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class HouseServiceImpl extends RavenEntityServiceImpl<String, House>
        implements HouseService {

    private HouseRepository houseRepository;

    @Inject
    public HouseServiceImpl(HouseRepository houseRepository) {
        super(houseRepository);
        this.houseRepository = houseRepository;
    }
}
