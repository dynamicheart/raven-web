package com.dynamicheart.raven.controller.app.serve.populator;

import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.controller.app.serve.field.CreateServeForm;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.model.serve.Serve;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.house.HouseService;
import com.dynamicheart.raven.services.member.MemberService;
import com.dynamicheart.raven.services.user.UserService;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@Component
public class CreateServeFormPopulator extends AbstractDataPopulator<CreateServeForm,Serve>{

    @Inject
    private UserService userService;

    @Inject
    private HouseService houseService;

    @Inject
    private MemberService memberService;

    @Override
    protected Serve createTarget() {
        return new Serve();
    }

    @Override
    public Serve populate(CreateServeForm createServeForm, Serve serve) throws ConversionException {
        List<Integer> types= Arrays.asList(Constants.SERVE_TYPE_ORDINARY,Constants.SERVE_TYPE_MAESTER,Constants.SERVE_TYPE_LORD);

        if(!userService.exists(createServeForm.getManId())||
                !houseService.exists(createServeForm.getHouseId())||
                !types.contains(createServeForm.getType()))
            throw new ConversionException();

        User user=userService.getById(createServeForm.getManId());
        House house=houseService.getById(createServeForm.getHouseId());
        Member member=memberService.findTopByHouseAndUser(house,user);

        if(member==null)
            throw new ConversionException();

        serve.setManId(createServeForm.getManId());
        serve.setHouseId(createServeForm.getHouseId());
        serve.setContent(createServeForm.getContent());
        serve.setStatus(Constants.SERVE_STATUS_HANDLING);
        serve.setType(createServeForm.getType());

        return serve;
    }
}
