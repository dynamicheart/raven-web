package com.dynamicheart.raven.controller.app.serve;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.app.raven.field.CreateRavenForm;
import com.dynamicheart.raven.controller.app.raven.field.RavenInfoFields;
import com.dynamicheart.raven.controller.app.serve.field.CreateServeForm;
import com.dynamicheart.raven.controller.app.serve.field.ServeInfoFields;
import com.dynamicheart.raven.controller.app.serve.populator.CreateServeFormPopulator;
import com.dynamicheart.raven.controller.common.model.GenericResponseBody;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.model.serve.Serve;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.house.HouseService;
import com.dynamicheart.raven.services.member.MemberService;
import com.dynamicheart.raven.services.serve.ServeService;
import com.sun.org.apache.regexp.internal.RE;
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
@RequestMapping("/api/v1/serves")
public class ServeController {

    @Inject
    private ServeService serveService;

    @Inject
    private MemberService memberService;

    @Inject
    private HouseService houseService;

    @Inject
    private CreateServeFormPopulator createServeFormPopulator;

    @RequestMapping(value = "/api/v1/serves", method = RequestMethod.POST)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = Serve.class, message = "Create a new serve")
    })
    public ResponseEntity<?> post(@RequestParam CreateServeForm createServeForm, @CurrentUser @ApiIgnore User currentUser) throws Exception{
        Serve serve=createServeFormPopulator.populate(createServeForm);
        House house=houseService.getById(serve.getManId());
        Member member=memberService.findTopByHouseAndUser(house,currentUser);

        Integer type=serve.getType();
        if(type.equals(Constants.SERVE_TYPE_ORDINARY)){
            if(!member.getRole().equals(Constants.MEMBER_ROLE_LORD))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        if(type.equals(Constants.MEMBER_ROLE_MAESTER)&&serve.getType().equals(Constants.SERVE_TYPE_MAESTER))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_REDUNDANT));

        if(type.equals(Constants.MEMBER_ROLE_LORD)&&serve.getType().equals(Constants.SERVE_TYPE_LORD))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_REDUNDANT));

        serveService.save(serve);

        return new ResponseEntity<>(serve, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/v1/users/{userId}/serves", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = Serve.class,  responseContainer = "List", message = "Get all serves")
    })
    public ResponseEntity<?> getAll(@PathVariable String userId, @CurrentUser @ApiIgnore User currentUser)throws Exception{
        if (!currentUser.getId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        List<Serve> serveList=serveService.getAllByManId(userId);

        return new ResponseEntity<>(serveList,HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/serves/{serveId}", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = Serve.class, message = "Get one raven")
    })
    public ResponseEntity<?> get(@PathVariable String serveId, @CurrentUser @ApiIgnore User currentUser) throws Exception{
        Serve serve = serveService.getById(serveId);
        if(serve == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        if(!serve.getManId().equals(currentUser.getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        return new ResponseEntity<>(serve, HttpStatus.OK);
    }
}
