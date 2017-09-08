package com.dynamicheart.raven.controller.app.house.populator;

import com.dynamicheart.raven.controller.app.house.field.CreateHouseForm;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class CreateHouseFormPopulator extends AbstractDataPopulator<CreateHouseForm, House> {
    @Override
    protected House createTarget() {
        return new House();
    }

    @Override
    public House populate(@NotNull CreateHouseForm createHouseForm,@NotNull House house) throws ConversionException {
        house.setName(createHouseForm.getName());
        house.setDescription(createHouseForm.getDescription());
        house.setTags(createHouseForm.getTags());
        return house;
    }
}
