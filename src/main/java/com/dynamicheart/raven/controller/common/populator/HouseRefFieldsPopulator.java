package com.dynamicheart.raven.controller.common.populator;

import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.controller.common.field.HouseRefFields;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class HouseRefFieldsPopulator extends AbstractDataPopulator<House, HouseRefFields>{
    @Override
    protected HouseRefFields createTarget() {
        return new HouseRefFields();
    }

    @Override
    public HouseRefFields populate(@NotNull House house,@NotNull HouseRefFields houseRefFields) throws ConversionException {
        houseRefFields.setId(house.getId());
        houseRefFields.setName(house.getName());
        houseRefFields.setSigil(house.getSigil());
        return houseRefFields;
    }
}
