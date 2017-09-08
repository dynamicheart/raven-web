package com.dynamicheart.raven.controller.app.house;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.app.house.field.CreateHouseForm;
import com.dynamicheart.raven.controller.app.house.field.HouseInfoFields;
import com.dynamicheart.raven.controller.app.house.field.UpdateHouseForm;
import com.dynamicheart.raven.controller.app.house.populator.CreateHouseFormPopulator;
import com.dynamicheart.raven.controller.app.house.populator.HouseInfoFieldsPopulator;
import com.dynamicheart.raven.controller.app.house.populator.UpdateHouseFormPopulator;
import com.dynamicheart.raven.controller.common.model.ErrorResponse;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.house.HouseService;
import com.dynamicheart.raven.services.member.MemberService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;

@RestController
@RequestMapping("/api/v1/houses")
public class HouseController {

    @Inject
    private HouseService houseService;

    @Inject
    private MemberService memberService;

    @Inject
    private HouseInfoFieldsPopulator houseInfoFieldsPopulator;

    @Inject
    private CreateHouseFormPopulator createHouseFormPopulator;

    @Inject
    private UpdateHouseFormPopulator updateHouseFormPopulator;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseInfoFields.class, message = "Get house info")
    })
    public ResponseEntity<?> get(@PathVariable String id, @CurrentUser @ApiIgnore User currentUser) throws Exception {
        House house = houseService.getById(id);
        if (house == null) {
            return new ResponseEntity<>(new ErrorResponse(Message.MESSAGE_NOT_FOUND), HttpStatus.NOT_FOUND);
        }

        if (!house.getPublicity() || memberService.findTopByHouseIdAndUser(house.getId(), currentUser) == null) {
            return new ResponseEntity<>(new ErrorResponse(Message.MESSAGE_NOT_FOUND), HttpStatus.NOT_FOUND);
        }

        HouseInfoFields houseInfoFields = houseInfoFieldsPopulator.populate(house);
        return new ResponseEntity<>(houseInfoFields, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 201, response = HouseInfoFields.class, message = "Create a new house")
    })
    public ResponseEntity<?> post(@CurrentUser @ApiIgnore User currentUser, @RequestBody CreateHouseForm createHouseForm) throws Exception {
        House house = createHouseFormPopulator.populate(createHouseForm);
        house = houseService.create(house, currentUser);

        Member member = new Member();
        member.setHouseId(house.getId());
        member.setUser(currentUser);
        member.setRole(Constants.MEMBER_ROLE_LORD);
        memberService.create(member);

        HouseInfoFields houseInfoFields = houseInfoFieldsPopulator.populate(house);

        return new ResponseEntity<>(houseInfoFields, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseInfoFields.class, message = "Update house info")
    })
    public ResponseEntity<?> put(@PathVariable String id, @CurrentUser @ApiIgnore User currentUser, @RequestBody UpdateHouseForm updateHouseForm) throws Exception {
        Member currentUserMember = memberService.findTopByHouseIdAndUser(id, currentUser);
        if(currentUserMember == null || !currentUserMember.getRole().equals(Constants.MEMBER_ROLE_LORD)){
            return new ResponseEntity<>(new ErrorResponse(Message.MESSAGE_FORBIDDEN), HttpStatus.FORBIDDEN);
        }

        House house = houseService.getById(id);

        if (house == null){
            return new ResponseEntity<>(new ErrorResponse(Message.MESSAGE_NOT_FOUND), HttpStatus.FORBIDDEN);
        }

        house = updateHouseFormPopulator.populate(updateHouseForm, house);
        house = houseService.save(house);

        HouseInfoFields houseInfoFields = houseInfoFieldsPopulator.populate(house);

        return new ResponseEntity<>(houseInfoFields, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseInfoFields.class, message = "Update the house")
    })
    public ResponseEntity<?> delete(@PathVariable String id, @CurrentUser @ApiIgnore User currentUser) throws Exception{
        Member currentUserMember = memberService.findTopByHouseIdAndUser(id, currentUser);
        if(currentUserMember == null || !currentUserMember.getRole().equals(Constants.MEMBER_ROLE_LORD)){
            return new ResponseEntity<>(new ErrorResponse(Message.MESSAGE_FORBIDDEN), HttpStatus.FORBIDDEN);
        }

        House house = houseService.getById(id);

        if (house == null){
            return new ResponseEntity<>(new ErrorResponse(Message.MESSAGE_NOT_FOUND), HttpStatus.FORBIDDEN);
        }

        houseService.delete(house);

        HouseInfoFields houseInfoFields = houseInfoFieldsPopulator.populate(house);

        return new ResponseEntity<>(houseInfoFields, HttpStatus.OK);
    }
}
