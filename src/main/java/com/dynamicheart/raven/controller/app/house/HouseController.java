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
import com.dynamicheart.raven.controller.common.model.GenericResponseBody;
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
import java.util.ArrayList;
import java.util.List;

@RestController
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

    @RequestMapping(value = "/api/v1/user/houses", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseInfoFields.class, responseContainer = "List", message = "Get all inravens")
    })
    public ResponseEntity<?> getAll(@CurrentUser @ApiIgnore User currentUser) throws Exception {
        List<Member> members = memberService.findByUser(currentUser);

        List<HouseInfoFields> houseInfoFieldsList = new ArrayList<>();

        for (Member member : members) {
            houseInfoFieldsList.add(houseInfoFieldsPopulator.populate(member.getHouse()));
        }

        return new ResponseEntity<>(houseInfoFieldsList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/houses/{id}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseInfoFields.class, message = "Get house info")
    })
    public ResponseEntity<?> get(@PathVariable String id) throws Exception {
        //change:查看不到disable的house
        House house = houseService.getActiveById(id);
        if(house==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        //change:无论公开私密都可以返回
        /*
        if ((!house.getPublicity()) && memberService.findTopByHouseAndUser(house, currentUser) == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }
        */

        HouseInfoFields houseInfoFields = houseInfoFieldsPopulator.populate(house);
        return new ResponseEntity<>(houseInfoFields, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/houses", method = RequestMethod.POST)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseInfoFields.class, message = "Create a new house")
    })
    public ResponseEntity<?> post(@CurrentUser @ApiIgnore User currentUser, @RequestBody CreateHouseForm createHouseForm) throws Exception {
        House house = createHouseFormPopulator.populate(createHouseForm);
        house = houseService.create(house, currentUser);

        //bug fix:change house member count
        house.setMemberNumbers(1);
        houseService.save(house);
        HouseInfoFields houseInfoFields = houseInfoFieldsPopulator.populate(house);

        return new ResponseEntity<>(houseInfoFields, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/v1/houses/{id}", method = RequestMethod.PUT)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseInfoFields.class, message = "Update house info")
    })
    public ResponseEntity<?> put(@PathVariable String id, @CurrentUser @ApiIgnore User currentUser, @RequestBody UpdateHouseForm updateHouseForm) throws Exception {
        //change:查看不到disable的house
        House house = houseService.getActiveById(id);
        if (house == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        Member currentUserMember = memberService.findTopByHouseAndUser(house, currentUser);
        if (currentUserMember == null || !currentUserMember.getRole().equals(Constants.MEMBER_ROLE_LORD)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        house = updateHouseFormPopulator.populate(updateHouseForm, house);
        house = houseService.save(house);

        HouseInfoFields houseInfoFields = houseInfoFieldsPopulator.populate(house);

        return new ResponseEntity<>(houseInfoFields, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/houses/{id}", method = RequestMethod.DELETE)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseInfoFields.class, message = "delete the house")
    })
    public ResponseEntity<?> delete(@PathVariable String id, @CurrentUser @ApiIgnore User currentUser) throws Exception {
        //change:查看不到disable的house
        House house = houseService.getActiveById(id);
        if (house == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        Member currentUserMember = memberService.findTopByHouseAndUser(house, currentUser);
        if (currentUserMember == null || !currentUserMember.getRole().equals(Constants.MEMBER_ROLE_LORD)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        houseService.delete(house);

        HouseInfoFields houseInfoFields = houseInfoFieldsPopulator.populate(house);

        return new ResponseEntity<>(houseInfoFields, HttpStatus.OK);
    }
}
