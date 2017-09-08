package com.dynamicheart.raven.controller.app.house.populator;

import com.dynamicheart.raven.controller.app.house.field.UpdateHouseForm;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class UpdateHouseFormPopulator extends AbstractDataPopulator<UpdateHouseForm, House> {
    @Override
    protected House createTarget() {
        return new House();
    }

    @Override
    public House populate(@NotNull UpdateHouseForm updateHouseForm, @NotNull House house) throws ConversionException {
        if(updateHouseForm.getName() != null){
            house.setName(updateHouseForm.getName());
        }

        if(updateHouseForm.getDescription() != null){
            house.setDescription(updateHouseForm.getDescription());
        }

        if(updateHouseForm.getTags() != null){
            house.setTags(updateHouseForm.getTags());
        }

        return house;
    }
}
