package com.dynamicheart.raven.controller.app.raven;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.app.raven.field.InRavenInfoFields;
import com.dynamicheart.raven.controller.app.raven.populator.InRavenInfoFieldsPopulator;
import com.dynamicheart.raven.controller.common.model.GenericResponseBody;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.raven.RavenService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class InRavenController {

    @Inject
    private RavenService ravenService;

    @Inject
    private InRavenInfoFieldsPopulator inRavenInfoFieldsPopulator;

    @RequestMapping(value = "/api/v1/users/{userId}/inravens", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = InRavenInfoFields.class,  responseContainer = "List", message = "Get all inravens")
    })
    public ResponseEntity<?> getAll(@PathVariable String userId,
                                    @RequestParam(required = false) Date dateAfter,
                                    @CurrentUser @ApiIgnore User currentUser) throws Exception {
        if (!currentUser.getId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        List<Raven> ravens;
        if (dateAfter == null) {
            ravens = ravenService.findByAddresseeId(userId);
        } else {
            ravens = ravenService.findByAddresseeIdAndCreatedDateAfter(userId, dateAfter);
        }

        List<InRavenInfoFields> inRavenInfoFieldsList = new ArrayList<>();
        for(Raven raven: ravens){
            inRavenInfoFieldsList.add(inRavenInfoFieldsPopulator.populate(raven, currentUser));
        }

        return new ResponseEntity<>(inRavenInfoFieldsList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/inravens/{ravenId}", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = InRavenInfoFields.class, message = "Get one inraven")
    })
    public ResponseEntity<?> get(@PathVariable String ravenId, @CurrentUser @ApiIgnore User currentUser) throws Exception {
        Raven raven = ravenService.getById(ravenId);
        if(raven == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        if(!raven.getAddresseeIds().contains(currentUser.getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        InRavenInfoFields inRavenInfoFields = inRavenInfoFieldsPopulator.populate(raven, currentUser);

        return new ResponseEntity<>(inRavenInfoFields, HttpStatus.OK);
    }
}
