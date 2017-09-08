package com.dynamicheart.raven.controller.app.house;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.app.house.field.HouseInfoFields;
import com.dynamicheart.raven.controller.app.house.populator.HouseInfoFieldsPopulator;
import com.dynamicheart.raven.controller.common.model.GenericResponseBody;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.house.HouseService;
import com.dynamicheart.raven.services.member.MemberService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/houses")
public class UserHouseController {

    @Inject
    private HouseService houseService;

    @Inject
    private MemberService memberService;

    @Inject
    private HouseInfoFieldsPopulator houseInfoFieldsPopulator;

    @RequestMapping(method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseInfoFields.class,  responseContainer = "List", message = "Get all inravens")
    })
    public ResponseEntity<?> getAll(@PathVariable String userId, @CurrentUser @ApiIgnore User currentUser) throws Exception{
        if(!userId.equals(currentUser.getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        List<Member> members = memberService.findByUser(currentUser);

        List<HouseInfoFields> houseInfoFieldsList = new ArrayList<>();

        for(Member member: members){
            houseInfoFieldsList.add(houseInfoFieldsPopulator.populate(member.getHouse()));
        }

        return new ResponseEntity<>(houseInfoFieldsList, HttpStatus.OK);
    }

}
