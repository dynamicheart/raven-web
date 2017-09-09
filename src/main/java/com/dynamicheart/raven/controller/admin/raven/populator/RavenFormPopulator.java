package com.dynamicheart.raven.controller.admin.raven.populator;

import com.dynamicheart.raven.controller.admin.raven.field.RavenForm;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class RavenFormPopulator extends AbstractDataPopulator<Raven,RavenForm>{


    @Override
    protected RavenForm createTarget() {
        return new RavenForm();
    }

    @Override
    public RavenForm populate(Raven raven, RavenForm ravenForm) throws ConversionException {
        ravenForm.setUpdatedDate(raven.getUpdatedDate());
        ravenForm.setCreatedDate(raven.getCreatedDate());
        ravenForm.setAddresseeIds(raven.getAddresseeIds());
        ravenForm.setAddresserId(raven.getAddresserId());
        ravenForm.setId(raven.getId());
        ravenForm.setAttachmentId(raven.getAttachmentId());
        ravenForm.setDescription(raven.getDescription());
        ravenForm.setHouseId(raven.getHouseId());
        ravenForm.setMoldId(raven.getMoldId());
        ravenForm.setMold(raven.getMold());
        ravenForm.setOptionPolls(raven.getOptionPolls());
        ravenForm.setTitle(raven.getTitle());
        ravenForm.setType(raven.getType());
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        ravenForm.setCreatedDateString(sdf.format(raven.getCreatedDate()));
        ravenForm.setUpdatedDateString(sdf.format(raven.getUpdatedDate()));
        return ravenForm;
    }

    public List<RavenForm> populateList(List<Raven> ravenList) throws ConversionException {
        List<RavenForm> ravenFormList=new ArrayList<RavenForm>();
        for(Raven raven:ravenList)
            ravenFormList.add(populate(raven,new RavenForm()));
        return ravenFormList;
    }
}
