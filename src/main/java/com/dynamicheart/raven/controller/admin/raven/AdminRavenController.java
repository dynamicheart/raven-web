package com.dynamicheart.raven.controller.admin.raven;

import com.dynamicheart.raven.authorization.annotation.AdminAuthorization;
import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.controller.admin.raven.field.RavenForm;
import com.dynamicheart.raven.controller.admin.raven.populator.RavenFormPopulator;
import com.dynamicheart.raven.controller.app.raven.populator.RavenInfoFieldsPopulator;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.house.HouseService;
import com.dynamicheart.raven.services.raven.RavenService;
import com.dynamicheart.raven.services.user.UserService;
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
@RequestMapping("/admin/api/v1/ravens")
public class AdminRavenController {
    @Inject
    private RavenService ravenService;

    @Inject
    private UserService userService;

    @Inject
    private HouseService houseService;

    @Inject
    private RavenFormPopulator ravenFormPopulator;

    @RequestMapping(value = "searchByAddresser/{userId}", method = RequestMethod.GET)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = "Get ravens by sender id")
    })
    public ResponseEntity<?> searchByAddresser(@PathVariable String userId)throws Exception{
        if(!userService.exists(userId))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        List<Raven> ravens = ravenService.findByAddresserId(userId);
        List<RavenForm> ravenFormList=ravenFormPopulator.populateList(ravens);

        return new ResponseEntity<>(ravenFormList,HttpStatus.OK);
    }

    @RequestMapping(value = "searchByDescription/{content}", method = RequestMethod.GET)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = "Get ravens by content")
    })
    public ResponseEntity<?> searchByDescription(@PathVariable String content)throws Exception{
        List<Raven> ravens = ravenService.findBySimilarContent(content);

        if(ravens.size()==0)
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        List<RavenForm> ravenFormList=ravenFormPopulator.populateList(ravens);
        return new ResponseEntity<>(ravenFormList,HttpStatus.OK);
    }

    @RequestMapping(value = "searchByHouse/{houseId}", method = RequestMethod.GET)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = "Get ravens by group id")
    })
    public ResponseEntity<?> searchByHouse(@PathVariable String houseId)throws Exception{
        if(!houseService.exists(houseId))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        List<Raven> ravens = ravenService.findByHouseId(houseId);

        List<RavenForm> ravenFormList=ravenFormPopulator.populateList(ravens);
        return new ResponseEntity<>(ravenFormList,HttpStatus.OK);
    }

    @RequestMapping(value = "allRavens", method = RequestMethod.GET)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = "Get ravens")
    })
    public ResponseEntity<?> allRavens()throws Exception{

        List<Raven> ravens = ravenService.findAll();

        List<RavenForm> ravenFormList=ravenFormPopulator.populateList(ravens);
        return new ResponseEntity<>(ravenFormList,HttpStatus.OK);
    }

    @RequestMapping(value = "getById/{ravenId}", method = RequestMethod.GET)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = RavenForm.class, message = "get raven by id")
    })
    public ResponseEntity<?> getById(@PathVariable String ravenId)throws Exception{
        if(!ravenService.exists(ravenId))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        Raven raven=ravenService.findById(ravenId);
        RavenForm ravenForm=ravenFormPopulator.populate(raven);

        return new ResponseEntity<>(ravenForm,HttpStatus.OK);
    }

    @RequestMapping(value = "deleteRaven/{ravenId}", method = RequestMethod.DELETE)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = RavenForm.class, message = "delete notification")
    })
    public ResponseEntity<?> deleteRaven(@PathVariable String ravenId)throws Exception{
        if(!ravenService.exists(ravenId))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        Raven raven=ravenService.findById(ravenId);
        RavenForm ravenForm=ravenFormPopulator.populate(raven);
        ravenService.delete(raven);

        return new ResponseEntity<>(ravenForm,HttpStatus.OK);
    }
}
