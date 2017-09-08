package com.dynamicheart.raven.controller.app.raven;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.app.raven.field.CreateRavenForm;
import com.dynamicheart.raven.controller.app.raven.field.RavenInfoFields;
import com.dynamicheart.raven.controller.app.raven.populator.CreateRavenFormPopulator;
import com.dynamicheart.raven.controller.app.raven.populator.RavenInfoFieldsPopulator;
import com.dynamicheart.raven.controller.common.model.ErrorResponseBody;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.leancloud.service.LeanCloudService;
import com.dynamicheart.raven.services.raven.RavenService;
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
@RequestMapping("/api/v1/users/{userId}/ravens")
public class RavenController {

    @Inject
    private RavenService ravenService;

    @Inject
    private RavenInfoFieldsPopulator ravenInfoFieldsPopulator;

    @Inject
    private CreateRavenFormPopulator createRavenFormPopulator;

    @Inject
    private LeanCloudService leanCloudService;

    @RequestMapping(method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = RavenInfoFields.class,  responseContainer = "List", message = "Get all ravens")
    })
    public ResponseEntity<?> getAll(@PathVariable String userId, @CurrentUser @ApiIgnore User currentUser)throws Exception{
        if (!currentUser.getId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        List<Raven> ravens = ravenService.findByAddresserId(userId);

        List<RavenInfoFields> ravenInfoFieldsList = new ArrayList<>();
        for(Raven raven: ravens){
            ravenInfoFieldsList.add(ravenInfoFieldsPopulator.populate(raven));
        }

        return new ResponseEntity<>(ravenInfoFieldsList,HttpStatus.OK);
    }

    @RequestMapping(value = "/{ravenId}", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = RavenInfoFields.class, message = "Get one raven")
    })
    ResponseEntity<?> get(@PathVariable String userId,
                          @PathVariable String ravenId,
                          @CurrentUser @ApiIgnore User currentUser) throws Exception{
        if (!currentUser.getId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        Raven raven = ravenService.getById(ravenId);
        if(raven == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        if(!raven.getAddresserId().equals(userId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        RavenInfoFields ravenInfoFields = ravenInfoFieldsPopulator.populate(raven);

        return new ResponseEntity<>(ravenInfoFields, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 201, response = RavenInfoFields.class, message = "Create a new raven")
    })
    ResponseEntity<?> post(@PathVariable String userId,
                           @RequestParam CreateRavenForm createRavenForm,
                           @CurrentUser @ApiIgnore User currentUser) throws Exception{
        if (!currentUser.getId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        Raven raven = createRavenFormPopulator.populate(createRavenForm);

        return null;
    }
}
