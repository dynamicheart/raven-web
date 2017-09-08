package com.dynamicheart.raven.controller.app.house.populator.admin;

import com.dynamicheart.raven.controller.app.house.field.admin.HouseDetailForm;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

@Component
public class HouseDetailFormPopulator extends AbstractDataPopulator<House, HouseDetailForm> {
    @Override
    protected HouseDetailForm createTarget() {
        return new HouseDetailForm();
    }

    @Override
    public HouseDetailForm populate(House house, HouseDetailForm houseDetailForm) throws ConversionException {
        houseDetailForm.setId(house.getId());
        houseDetailForm.setCapacity(house.getCapacity());
        houseDetailForm.setCreatedDate(house.getCreatedDate());
        houseDetailForm.setDescription(house.getDescription());
        houseDetailForm.setFounderId(house.getFounderId());
        houseDetailForm.setLastModifiedDate(house.getLastModifiedDate());
        houseDetailForm.setName(house.getName());
        houseDetailForm.setPublicity(house.getPublicity());
        houseDetailForm.setSigil(house.getSigil());
        houseDetailForm.setStatus(house.getStatus());
        houseDetailForm.setTags(house.getTags());
        return houseDetailForm;
    }

    public HouseDetailForm populate(House house, HouseDetailForm houseDetailForm, String master) throws ConversionException {
        populate(house,houseDetailForm);
        houseDetailForm.setMaster(master);
        return houseDetailForm;
    }
}
