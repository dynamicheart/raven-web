package com.dynamicheart.raven.controller.app.raven;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.app.raven.field.CreateRavenForm;
import com.dynamicheart.raven.controller.app.raven.field.RavenInfoFields;
import com.dynamicheart.raven.controller.app.raven.populator.CreateRavenFormPopulator;
import com.dynamicheart.raven.controller.app.raven.populator.RavenInfoFieldsPopulator;
import com.dynamicheart.raven.controller.common.model.GenericResponseBody;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.leancloud.service.LeanCloudService;
import com.dynamicheart.raven.services.house.HouseService;
import com.dynamicheart.raven.services.member.MemberService;
import com.dynamicheart.raven.services.raven.RavenService;
import com.dynamicheart.raven.utils.exception.ServiceException;
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
public class RavenController {

    @Inject
    private RavenService ravenService;

    @Inject
    private MemberService memberService;

    @Inject
    private HouseService houseService;

    @Inject
    private RavenInfoFieldsPopulator ravenInfoFieldsPopulator;

    @Inject
    private CreateRavenFormPopulator createRavenFormPopulator;

    @Inject
    private LeanCloudService leanCloudService;

    @RequestMapping(value = "/api/v1/user/ravens", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = RavenInfoFields.class,  responseContainer = "List", message = "Get all ravens")
    })
    public ResponseEntity<?> getAll(@CurrentUser @ApiIgnore User currentUser)throws Exception{
        List<Raven> ravens = ravenService.findByAddresserId(currentUser.getId());

        List<RavenInfoFields> ravenInfoFieldsList = new ArrayList<>();
        for(Raven raven: ravens){
            ravenInfoFieldsList.add(ravenInfoFieldsPopulator.populate(raven));
        }

        return new ResponseEntity<>(ravenInfoFieldsList,HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/ravens/{ravenId}", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = RavenInfoFields.class, message = "Get one raven")
    })
    public ResponseEntity<?> get(@PathVariable String ravenId, @CurrentUser @ApiIgnore User currentUser) throws Exception{
        Raven raven = ravenService.getById(ravenId);
        if(raven == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        if(!raven.getAddresserId().equals(currentUser.getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        RavenInfoFields ravenInfoFields = ravenInfoFieldsPopulator.populate(raven);

        return new ResponseEntity<>(ravenInfoFields, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/ravens", method = RequestMethod.POST)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = RavenInfoFields.class, message = "Create a new raven")
    })
    //changes:requestparam or requestbody
    public ResponseEntity<?> post(@RequestBody CreateRavenForm createRavenForm, @CurrentUser @ApiIgnore User currentUser) throws Exception{
        Raven raven = createRavenFormPopulator.populate(createRavenForm);
        House house = houseService.getById(raven.getHouseId());
        Member member = memberService.findTopByHouseAndUser(house, currentUser);
        if(member == null || (!member.getRole().equals(Constants.MEMBER_ROLE_LORD) && !member.getRole().equals(Constants.MEMBER_ROLE_MAESTER))){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        //feature:检测通知发送情况
        try {
            leanCloudService.send(raven, currentUser);
        }catch(ServiceException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_LEANCLOUD_ERROR));
        }
        raven = ravenService.save(raven);
        RavenInfoFields ravenInfoFields = ravenInfoFieldsPopulator.populate(raven);

        return new ResponseEntity<>(ravenInfoFields, HttpStatus.CREATED);
    }
}
