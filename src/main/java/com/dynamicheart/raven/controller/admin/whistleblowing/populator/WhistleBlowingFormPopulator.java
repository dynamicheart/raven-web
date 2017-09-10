package com.dynamicheart.raven.controller.admin.whistleblowing.populator;

import com.dynamicheart.raven.controller.admin.whistleblowing.field.WhistleBlowingForm;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.whistleblowing.WhistleBlowing;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class WhistleBlowingFormPopulator extends AbstractDataPopulator<WhistleBlowing,WhistleBlowingForm>{

    @Override
    protected WhistleBlowingForm createTarget() {
        return new WhistleBlowingForm();
    }

    @Override
    public WhistleBlowingForm populate(WhistleBlowing whistleBlowing, WhistleBlowingForm whistleBlowingForm) throws ConversionException {
        whistleBlowingForm.setCreatedDate(whistleBlowing.getCreatedDate());
        whistleBlowingForm.setRavenId(whistleBlowing.getRavenId());
        whistleBlowingForm.setStatus(whistleBlowing.getStatus());
        whistleBlowingForm.setWhistleblowerId(whistleBlowing.getWhistleblowerId());
        whistleBlowingForm.setDescription(whistleBlowing.getDescription());
        whistleBlowingForm.setId(whistleBlowing.getId());
        whistleBlowingForm.setType(whistleBlowing.getType());

        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        whistleBlowingForm.setCreatedDateString(sdf.format(whistleBlowing.getCreatedDate()));
        return whistleBlowingForm;
    }

    public List<WhistleBlowingForm> populateList(List<WhistleBlowing> whistleBlowingList) throws ConversionException{
        List<WhistleBlowingForm> whistleBlowingFormList=new ArrayList<>();
        for(WhistleBlowing whistleBlowing:whistleBlowingList)
            whistleBlowingFormList.add(populate(whistleBlowing,new WhistleBlowingForm()));
        return whistleBlowingFormList;
    }
}
