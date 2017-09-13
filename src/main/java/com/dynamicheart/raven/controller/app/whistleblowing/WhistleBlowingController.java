package com.dynamicheart.raven.controller.app.whistleblowing;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.app.whistleblowing.field.CreateWhistleBlowingForm;
import com.dynamicheart.raven.controller.app.whistleblowing.populator.CreateWhistleBlowingFormPopulator;
import com.dynamicheart.raven.controller.common.model.GenericResponseBody;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.model.whistleblowing.WhistleBlowing;
import com.dynamicheart.raven.services.raven.RavenService;
import com.dynamicheart.raven.services.whistleblowing.WhistleBlowingService;
import com.dynamicheart.raven.utils.exception.ConversionException;
import com.dynamicheart.raven.utils.exception.ServiceException;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;

@RestController
@RequestMapping("/api/v1/whistleblowings")
public class WhistleBlowingController {

    @Inject
    private RavenService ravenService;

    @Inject
    private CreateWhistleBlowingFormPopulator createWhistleBlowingFormPopulator;

    @Inject
    private WhistleBlowingService whistleBlowingService;

    @RequestMapping(method = RequestMethod.POST)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 201, response = WhistleBlowing.class, message = "Create a new whistleblowing")
    })
    public ResponseEntity<?> post(@CurrentUser @ApiIgnore User currentUser, @RequestBody CreateWhistleBlowingForm createWhistleBlowingForm) throws ConversionException, ServiceException {
        WhistleBlowing whistleBlowing=createWhistleBlowingFormPopulator.populate(createWhistleBlowingForm);

        if(!ravenService.exists(whistleBlowing.getRavenId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        whistleBlowing.setStatus(Constants.WHISTLE_BLOWING_STATUS_HANDLING);
        whistleBlowingService.save(whistleBlowing);

        return new ResponseEntity<>(whistleBlowing, HttpStatus.CREATED);
    }
}
