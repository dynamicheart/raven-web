package com.dynamicheart.raven.controller.app.raven.populator;

import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.controller.app.raven.field.CreateRavenForm;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.services.user.UserService;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class CreateRavenFormPopulator extends AbstractDataPopulator<CreateRavenForm, Raven> {

    @Inject
    private UserService userService;

    @Override
    protected Raven createTarget() {
        return new Raven();
    }

    @Override
    public Raven populate(CreateRavenForm createRavenForm, Raven raven) throws ConversionException {
        if(createRavenForm.getAddresserId() != null && userService.exists(createRavenForm.getAddresserId())){
            raven.setAddresserId(createRavenForm.getAddresserId());
        }else {
            throw new ConversionException();
        }

        if(!createRavenForm.getAddresseeIds().isEmpty() && userService.exists(createRavenForm.getAddresseeIds())){
            raven.setAddresseeIds(createRavenForm.getAddresseeIds());
        }else {
            throw new ConversionException();
        }

        raven.setTitle(createRavenForm.getTitle());
        raven.setDescription(createRavenForm.getDescription());

        Integer type = createRavenForm.getType();

        if(!type.equals(Constants.RAVEN_TYPE_ORDINARY) && !type.equals(Constants.RAVEN_TYPE_POLLS)){
            throw new ConversionException();
        }

        if(createRavenForm.getType().equals(Constants.RAVEN_TYPE_POLLS)){
            raven.setOptionPolls(createRavenForm.getOptionPolls());
        }

        if(createRavenForm.isMold() && createRavenForm.getMoldId() != null){
            raven.setMold(true);
            raven.setMoldId(createRavenForm.getMoldId());
        }else if (createRavenForm.isMold() && createRavenForm.getMoldId() != null){
            throw new ConversionException();
        }

        //bug fix: houseId is null
        raven.setHouseId(createRavenForm.getHouseId());
        raven.setAttachmentId(createRavenForm.getAttachmentId());
        return raven;
    }
}
