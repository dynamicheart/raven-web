package com.dynamicheart.raven.controller.app.raven.populator;

import com.dynamicheart.raven.controller.app.raven.field.InRavenInfoFields;
import com.dynamicheart.raven.controller.common.populator.HouseRefFieldsPopulator;
import com.dynamicheart.raven.controller.common.populator.ReplyRefFieldsPopulator;
import com.dynamicheart.raven.controller.common.populator.UserRefFieldsPopulator;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.mold.Mold;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.model.reply.Reply;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.house.HouseService;
import com.dynamicheart.raven.services.mold.MoldService;
import com.dynamicheart.raven.services.reply.ReplyService;
import com.dynamicheart.raven.services.user.UserService;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

@Component
public class InRavenInfoFieldsPopulator {

    @Inject
    private HouseService houseService;

    @Inject
    private ReplyService replyService;

    @Inject
    private UserService userService;

    @Inject
    private MoldService moldService;

    @Inject
    private HouseRefFieldsPopulator houseRefFieldsPopulator;

    @Inject
    private ReplyRefFieldsPopulator replyRefFieldsPopulator;

    @Inject
    private UserRefFieldsPopulator userRefFieldsPopulator;

    protected InRavenInfoFields createTarget() {
        return new InRavenInfoFields();
    }

    public InRavenInfoFields populate(Raven raven, User currentUser) throws ConversionException {
        return populate(raven, createTarget(), currentUser);
    }

    public InRavenInfoFields populate(@NotNull Raven raven,@NotNull InRavenInfoFields inRavenInfoFields,@NotNull User currentUser) throws ConversionException {
        inRavenInfoFields.setId(raven.getId());
        inRavenInfoFields.setTitle(raven.getTitle());
        inRavenInfoFields.setDescription(raven.getDescription());
        inRavenInfoFields.setType(raven.getType());
        inRavenInfoFields.setOptionPolls(raven.getOptionPolls());
        inRavenInfoFields.setAttachmentId(raven.getAttachmentId());
        inRavenInfoFields.setCreatedDate(raven.getCreatedDate());

        House house = houseService.getById(raven.getHouseId());
        if(house != null){
            inRavenInfoFields.setHouse(houseRefFieldsPopulator.populate(house));
        }

        User addresser = userService.getById(raven.getId());
        if(addresser != null){
            inRavenInfoFields.setAddresser(userRefFieldsPopulator.populate(addresser));
        }

        Reply reply = replyService.findTopByRavenIdAndUserId(raven.getId(), currentUser.getId());
        if(reply != null){
            inRavenInfoFields.setReplyRefFields(replyRefFieldsPopulator.populate(reply));
        }

        Mold mold = moldService.getById(raven.getMoldId());
        if(raven.isMold() && mold != null){
            inRavenInfoFields.setMold(true);
            inRavenInfoFields.setMoldContent(mold.getContent());
        }

        return inRavenInfoFields;
    }
}
