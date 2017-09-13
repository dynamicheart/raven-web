package com.dynamicheart.raven.controller.app.whistleblowing.populator;

import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.controller.app.whistleblowing.field.CreateWhistleBlowingForm;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.whistleblowing.WhistleBlowing;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CreateWhistleBlowingFormPopulator extends AbstractDataPopulator<CreateWhistleBlowingForm,WhistleBlowing> {
    @Override
    protected WhistleBlowing createTarget() {
        return new WhistleBlowing();
    }

    @Override
    public WhistleBlowing populate(CreateWhistleBlowingForm createWhistleBlowingForm, WhistleBlowing whistleBlowing) throws ConversionException {
        if(createWhistleBlowingForm.getRavenId()==null||!types.contains(createWhistleBlowingForm.getType()))
            throw new ConversionException();

        whistleBlowing.setType(createWhistleBlowingForm.getType());
        whistleBlowing.setDescription(createWhistleBlowingForm.getDescription());
        whistleBlowing.setRavenId(createWhistleBlowingForm.getRavenId());
        return whistleBlowing;
    }

    private List<Integer> types= Arrays.asList(Constants.WHISTLE_BLOWING_TYPE_ADVERTISEMENT,Constants.WHISTLE_BLOWING_TYPE_FALSE,Constants.WHISTLE_BLOWING_TYPE_HARM,
            Constants.WHISTLE_BLOWING_TYPE_ILLEGAL,Constants.WHISTLE_BLOWING_TYPE_OTHERS,Constants.WHISTLE_BLOWING_TYPE_PORN);
}
