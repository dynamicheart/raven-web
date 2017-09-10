package com.dynamicheart.raven.controller.admin.whistleblowing;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.admin.whistleblowing.field.WhistleBlowingForm;
import com.dynamicheart.raven.controller.admin.whistleblowing.populator.WhistleBlowingFormPopulator;
import com.dynamicheart.raven.controller.common.model.GenericResponseBody;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.model.whistleblowing.WhistleBlowing;
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
import java.util.List;

@RestController
@RequestMapping("/admin/api/v1/whistleblowings")
public class AdminWhistleBlowingController {
    @Inject
    private WhistleBlowingService whistleBlowingService;

    @Inject
    private WhistleBlowingFormPopulator whistleBlowingFormPopulator;

    @RequestMapping(name="allWhistleBlowing", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = "query all complaints")
    })
    public ResponseEntity<?> allWhistleBlowing() throws ConversionException {
        List<WhistleBlowing> whistleBlowingList=whistleBlowingService.getAll();

        List<WhistleBlowingForm> whistleBlowingFormList=whistleBlowingFormPopulator.populateList(whistleBlowingList);
        return new ResponseEntity<>(whistleBlowingFormList,HttpStatus.OK);
    }

    @RequestMapping(name="closeWhistleBlowing/{complaintId}", method = RequestMethod.PUT)
    @ApiResponses({
            @ApiResponse(code = 200, response = WhistleBlowingForm.class, message = "close a complaint")
    })
    public ResponseEntity<?> closeWhistleBlowing(@PathVariable String complaintId) throws ServiceException, ConversionException {
        if(!whistleBlowingService.exists(complaintId))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);


        WhistleBlowing whistleBlowing=whistleBlowingService.getById(complaintId);
        whistleBlowing.setStatus(Constants.WHISTLE_BLOWING_STATUS_FINISHED);
        whistleBlowingService.save(whistleBlowing);

        WhistleBlowingForm whistleBlowingForm=whistleBlowingFormPopulator.populate(whistleBlowing);

        return new ResponseEntity<>(whistleBlowingForm,HttpStatus.OK);
    }
}
