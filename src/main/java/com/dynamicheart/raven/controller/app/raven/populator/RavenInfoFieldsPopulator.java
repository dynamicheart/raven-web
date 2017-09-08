package com.dynamicheart.raven.controller.app.raven.populator;

import com.dynamicheart.raven.controller.app.raven.field.RavenInfoFields;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.controller.common.populator.HouseRefFieldsPopulator;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.services.house.HouseService;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

@Component
public class RavenInfoFieldsPopulator extends AbstractDataPopulator<Raven, RavenInfoFields>{

    @Inject
    private HouseService houseService;

    @Inject
    private HouseRefFieldsPopulator houseRefFieldsPopulator;

    @Override
    protected RavenInfoFields createTarget() {
        return new RavenInfoFields();
    }

    @Override
    public RavenInfoFields populate(@NotNull Raven raven,@NotNull RavenInfoFields ravenInfoFields) throws ConversionException {
        ravenInfoFields.setId(raven.getId());
        ravenInfoFields.setTitle(raven.getTitle());
        ravenInfoFields.setDescription(raven.getDescription());
        ravenInfoFields.setType(raven.getType());
        ravenInfoFields.setOptionPolls(raven.getOptionPolls());
        ravenInfoFields.setAttachmentId(raven.getAttachmentId());
        ravenInfoFields.setCreatedDate(raven.getCreatedDate());
        ravenInfoFields.setMold(raven.isMold());
        ravenInfoFields.setMoldId(raven.getMoldId());

        House house = houseService.getById(raven.getHouseId());
        if(house != null){
            ravenInfoFields.setHouse(houseRefFieldsPopulator.populate(house));
        }

        return ravenInfoFields;
    }
}
