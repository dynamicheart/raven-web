package com.dynamicheart.raven.controller.admin.whistleblowing;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.common.model.GenericResponseBody;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.model.whistleblowing.WhistleBlowing;
import com.dynamicheart.raven.services.whistleblowing.WhistleBlowingService;
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
import java.util.List;

@RestController
@RequestMapping("/admin/api/v1/whistleblowings")
public class AdminWhistleBlowingController {
    @Inject
    private WhistleBlowingService whistleBlowingService;

    @RequestMapping(name="allWhistleBlowing", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = "query all complaints")
    })
    public ResponseEntity<?> allWhistleBlowing(){
        List<WhistleBlowing> whistleBlowingList=whistleBlowingService.getAll();

        return new ResponseEntity<>(whistleBlowingList,HttpStatus.OK);
    }
}
