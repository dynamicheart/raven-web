package com.dynamicheart.raven.controller.app.house.populator;

import com.dynamicheart.raven.controller.app.house.field.HouseInfoFields;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.controller.common.field.UserRefFields;
import com.dynamicheart.raven.controller.common.populator.MemberRefFieldsListPopulator;
import com.dynamicheart.raven.controller.common.populator.UserRefFieldsPopulator;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.member.MemberService;
import com.dynamicheart.raven.services.user.UserService;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component
public class HouseInfoFieldsPopulator extends AbstractDataPopulator<House, HouseInfoFields> {

    @Inject
    private MemberRefFieldsListPopulator memberRefFieldsListPopulator;

    @Inject
    private UserRefFieldsPopulator userRefFieldsPopulator;

    @Inject
    private UserService userService;

    @Inject
    private MemberService memberService;

    @Override
    protected HouseInfoFields createTarget() {
        return new HouseInfoFields();
    }

    @Override
    public HouseInfoFields populate(@NotNull House house, @NotNull HouseInfoFields houseInfoFields) throws ConversionException {
        houseInfoFields.setId(house.getId());
        houseInfoFields.setName(house.getName());
        houseInfoFields.setStatus(house.getStatus());
        houseInfoFields.setCapacity(house.getCapacity());
        houseInfoFields.setMemberNumbers(house.getMemberNumbers());
        houseInfoFields.setDescription(house.getDescription());
        houseInfoFields.setPublicity(house.getPublicity());
        houseInfoFields.setTags(house.getTags());
        houseInfoFields.setCreatedDate(house.getCreatedDate());

        //bug fix:founderId可能为null
        if(house.getFounderId()!=null) {
            User founder = userService.getById(house.getFounderId());
            if (founder != null) {
                houseInfoFields.setFounder(userRefFieldsPopulator.populate(founder, new UserRefFields()));
            }
        }

        List<Member> members = memberService.findByHouse(house);
        houseInfoFields.setMembers(memberRefFieldsListPopulator.populate(members));

        return houseInfoFields;
    }
}
