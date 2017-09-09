package com.dynamicheart.raven.controller.app.user;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.authorization.manager.TokenManager;
import com.dynamicheart.raven.authorization.model.TokenModel;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.app.user.field.CreateUserForm;
import com.dynamicheart.raven.controller.app.user.field.UpdateUserForm;
import com.dynamicheart.raven.controller.app.user.field.UserInfoFields;
import com.dynamicheart.raven.controller.app.user.populator.CreateUserFormPopulator;
import com.dynamicheart.raven.controller.app.user.populator.UpdateUserFormPopulator;
import com.dynamicheart.raven.controller.app.user.populator.UserInfoFieldsPopulator;
import com.dynamicheart.raven.controller.common.model.GenericResponseBody;
import com.dynamicheart.raven.leancloud.manager.InstallationManager;
import com.dynamicheart.raven.leancloud.model.installation.InstallationModel;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.user.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Inject
    private UserService userService;

    @Inject
    private TokenManager tokenManager;

    @Inject
    private InstallationManager installationManager;

    @Inject
    private UserInfoFieldsPopulator infoFieldsPopulator;

    @Inject
    private CreateUserFormPopulator createUserFormPopulator;

    @Inject
    private UpdateUserFormPopulator updateUserFormPopulator;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = UserInfoFields.class, message = "Delete user")
    })
    public ResponseEntity<?> get(@PathVariable String id, @CurrentUser @ApiIgnore User currentUser) throws Exception{
        if(!id.equals(currentUser.getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        UserInfoFields userInfoFields = infoFieldsPopulator.populate(currentUser);

        return new ResponseEntity<>(userInfoFields, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 200, response = UserInfoFields.class, message = "Create a user")
    })
    public ResponseEntity<?> post(@RequestBody CreateUserForm createUserForm) throws Exception{

        User user = createUserFormPopulator.populate(createUserForm);

        //TODO check duplicate

        user = userService.create(user);

        UserInfoFields userInfoFields = infoFieldsPopulator.populate(user);

        return new ResponseEntity<>(userInfoFields, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = UserInfoFields.class, message = "Update user")
    })
    public ResponseEntity<?> put(@PathVariable String id, @CurrentUser @ApiIgnore User currentUser, @RequestBody UpdateUserForm updateUserForm) throws Exception{
        if(!id.equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        currentUser = updateUserFormPopulator.populate(updateUserForm);
        currentUser = userService.save(currentUser);

        UserInfoFields userInfoFields = infoFieldsPopulator.populate(currentUser);

        return new ResponseEntity<>(userInfoFields, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = UserInfoFields.class, message = "Delete user")
    })
    public ResponseEntity<?> delete(@PathVariable String id, @CurrentUser @ApiIgnore User currentUser) throws Exception{
        if(!id.equals(currentUser.getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        userService.delete(currentUser);

        UserInfoFields userInfoFields = infoFieldsPopulator.populate(currentUser);

        return new ResponseEntity<>(userInfoFields, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/installations", method = RequestMethod.POST)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = InstallationModel.class, message = "Update installation id")
    })
    public ResponseEntity<?> postInstallationId(@PathVariable String id,
                                                @RequestParam String installationId,
                                                @CurrentUser @ApiIgnore User currentUser) throws Exception{
        if(!id.equals(currentUser.getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        InstallationModel installation = installationManager.save(id, installationId);
        return new ResponseEntity<>(installation, HttpStatus.OK);
    }
}
