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
import com.dynamicheart.raven.services.user.UserService;
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
public class ServeController {

    @Inject
    private ServeService serveService;

    @Inject
    private MemberService memberService;

    @Inject
    private HouseService houseService;

    @Inject
    private UserService userService;

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
            if(member!=null)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_REDUNDANT));
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
            @ApiResponse(code = 200, response = Serve.class, message = "Get one serve")
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

    @RequestMapping(value = "/api/v1/serves/{houseId}", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = Serve.class, responseContainer = "List", message = "Get available serve in one house")
    })
    public ResponseEntity<?> getByHouse(@PathVariable String houseId, @CurrentUser @ApiIgnore User currentUser) throws Exception{
        if(!houseService.exists(houseId))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));

        House house=houseService.getById(houseId);
        Member member=memberService.findTopByHouseAndUser(house,currentUser);

        if(member==null||!(member.getRole().equals(Constants.MEMBER_ROLE_LORD)))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));

        List<Serve> serveList=serveService.getAllHandingOrdinaryByHouseId(houseId);

        return new ResponseEntity<>(serveList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/serves/{serveId}/{judge}", method = RequestMethod.PUT)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = Serve.class, message = "accept or reject one serve")
    })
    public ResponseEntity<?> judgeServe(@PathVariable String serveId,@PathVariable String judge, @CurrentUser @ApiIgnore User currentUser) throws Exception{
        if(!serveService.exists(serveId))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));

        Serve serve=serveService.getById(serveId);
        if(serve.getType()!=Constants.SERVE_TYPE_ORDINARY||serve.getStatus()!=Constants.SERVE_STATUS_HANDLING)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));

        if(judge.equals("1")) {
            House house=houseService.getById(serve.getHouseId());
            User user=userService.getById(serve.getManId());

            if(house==null||user==null||user.getStatus().equals(Constants.USER_STATUS_DISABLE))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));

            Member curMember=memberService.findTopByHouseAndUser(house,currentUser);
            if(currentUser.getStatus().equals(Constants.USER_STATUS_DISABLE)||curMember==null||!curMember.getRole().equals(Constants.MEMBER_ROLE_LORD))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));

            serve.setStatus(Constants.SERVE_STATUS_ACCEPTED);
            Member member=new Member();
            member.setUser(user);
            member.setHouse(house);
            member.setRole(Constants.MEMBER_ROLE_ORDINARY);
            memberService.save(member);
            
            house.setMemberNumbers(house.getMemberNumbers()+1);
            houseService.save(house);
        }
        else if(judge.equals("0"))
            serve.setStatus(Constants.SERVE_STATUS_REFUSED);
        serveService.save(serve);

        return new ResponseEntity<>(serve, HttpStatus.OK);
    }
}
