package com.dynamicheart.raven.controller.app.whistleblowing;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.common.model.ErrorResponseBody;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.model.whistleblowing.WhistleBlowing;
import com.dynamicheart.raven.services.raven.RavenService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;

@RestController
@RequestMapping("/api/v1/whistleblowings")
public class WhistleBlowingController {

    @Inject
    private RavenService ravenService;

    @RequestMapping(method = RequestMethod.POST)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 201, response = WhistleBlowing.class, message = "Create a new whistleblowing")
    })
    public ResponseEntity<?> post(@CurrentUser @ApiIgnore User currentUser, @RequestBody String ravenId){
        if(!ravenService.exists(ravenId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        WhistleBlowing whistleBlowing = new WhistleBlowing();
        whistleBlowing.setWhistleblowerId(currentUser.getId());
        whistleBlowing.setRavenId(ravenId);

        return new ResponseEntity<>(whistleBlowing, HttpStatus.CREATED);
    }
}
