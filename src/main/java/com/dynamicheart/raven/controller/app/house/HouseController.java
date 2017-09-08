package com.dynamicheart.raven.controller.app.house;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.controller.app.house.field.HouseInfoFields;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.repositories.house.HouseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/api/v1/houses")
public class HouseController {

    @Inject
    private HouseRepository houseRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Authorization
    public ResponseEntity<HouseInfoFields> get(@PathVariable String id){
        House house = houseRepository.findOne(id);
        return new ResponseEntity<HouseInfoFields>(HttpStatus.OK);
    }
}
