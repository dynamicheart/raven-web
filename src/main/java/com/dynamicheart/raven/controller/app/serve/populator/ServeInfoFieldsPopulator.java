package com.dynamicheart.raven.controller.app.serve.populator;

import com.dynamicheart.raven.controller.app.serve.field.ServeInfoFields;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.controller.common.field.HouseRefFields;
import com.dynamicheart.raven.controller.common.field.UserRefFields;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.serve.Serve;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.house.HouseService;
import com.dynamicheart.raven.services.user.UserService;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ServeInfoFieldsPopulator extends AbstractDataPopulator<Serve,ServeInfoFields>{
    @Inject
    private UserService userService;

    @Inject
    private HouseService houseService;

    @Override
    protected ServeInfoFields createTarget() {
        return new ServeInfoFields();
    }

    @Override
    public ServeInfoFields populate(Serve serve, ServeInfoFields serveInfoFields) throws ConversionException {
        serveInfoFields.setContent(serve.getContent());
        serveInfoFields.setCreatedDate(serve.getCreatedDate());
        serveInfoFields.setHouseId(serve.getHouseId());
        serveInfoFields.setId(serve.getId());
        serveInfoFields.setManId(serve.getManId());
        serveInfoFields.setStatus(serve.getStatus());
        serveInfoFields.setType(serve.getType());

        if(serve.getManId()!=null) {
            User user = userService.getById(serve.getManId());
            if(user!=null){
                UserRefFields userRefFields=new UserRefFields();
                userRefFields.setId(user.getId());
                userRefFields.setUsername(user.getUsername());

                serveInfoFields.setMan(userRefFields);
            }
        }

        if(serve.getHouseId()!=null){
            House house=houseService.getById(serve.getHouseId());
            if(house!=null){
                HouseRefFields houseRefFields=new HouseRefFields();
                houseRefFields.setId(house.getId());
                houseRefFields.setName(house.getName());
                houseRefFields.setSigil(house.getSigil());

                serveInfoFields.setHouse(houseRefFields);
            }
        }

        return serveInfoFields;
    }
}
