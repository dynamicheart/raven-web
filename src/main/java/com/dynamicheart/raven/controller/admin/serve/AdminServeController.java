package com.dynamicheart.raven.controller.admin.serve;


import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.model.serve.Serve;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.house.HouseService;
import com.dynamicheart.raven.services.member.MemberService;
import com.dynamicheart.raven.services.serve.ServeService;
import com.dynamicheart.raven.services.user.UserService;
import com.dynamicheart.raven.utils.exception.ServiceException;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/admin/api/v1/serves")
public class AdminServeController {
    @Inject
    private ServeService serveService;

    @Inject
    private UserService userService;

    @Inject
    private HouseService houseService;

    @Inject
    private MemberService memberService;

    @RequestMapping(name="allServes", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = "query all applications")
    })
    public ResponseEntity<?> allServes(){
        List<Serve> serveList=serveService.getAllHandling();
        return new ResponseEntity<>(serveList, HttpStatus.OK);
    }


    @RequestMapping(name="judgeServe/{serveId}/{judge}", method = RequestMethod.PUT)
    @ApiResponses({
            @ApiResponse(code = 200, response = Serve.class, message = "judge an application: 1 for approval, 0 for rejection")
    })
    public ResponseEntity<?> judgeServe(@PathVariable String serveId,@PathVariable String judge) throws ServiceException {
        if(!serveService.exists(serveId))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        Serve serve=serveService.getById(serveId);
        if(judge.equals("1")) {
            serve.setStatus(Constants.SERVE_STATUS_ACCEPTED);
            House house=houseService.getById(serve.getHouseId());
            User user=userService.getById(serve.getManId());
            Member member=memberService.findTopByHouseAndUser(house,user);

            //NOTE that currently you can only be accepted to be a MAESTER

            member.setRole(Constants.MEMBER_ROLE_MAESTER);
            memberService.save(member);
        }
        else if(judge.equals("0"))
            serve.setStatus(Constants.SERVE_STATUS_REFUSED);
        serveService.save(serve);

        return new ResponseEntity<>(serve, HttpStatus.OK);
    }
}
