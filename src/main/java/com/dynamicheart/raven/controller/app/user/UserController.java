package com.dynamicheart.raven.controller.app.user;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.controller.app.user.field.CreateUserForm;
import com.dynamicheart.raven.controller.app.user.field.UpdateUserForm;
import com.dynamicheart.raven.controller.app.user.field.UserInfoFields;
import com.dynamicheart.raven.controller.app.user.populator.CreateUserFormPopulator;
import com.dynamicheart.raven.controller.app.user.populator.UpdateUserFormPopulator;
import com.dynamicheart.raven.controller.app.user.populator.UserInfoFieldsPopulator;
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
import javax.validation.Valid;

@RestController
public class UserController {

    @Inject
    private UserService userService;

    @Inject
    private InstallationManager installationManager;

    @Inject
    private UserInfoFieldsPopulator infoFieldsPopulator;

    @Inject
    private CreateUserFormPopulator createUserFormPopulator;

    @Inject
    private UpdateUserFormPopulator updateUserFormPopulator;

    @RequestMapping(value = "/api/v1/user", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = UserInfoFields.class, message = "Get user info")
    })
    public ResponseEntity<?> get(@CurrentUser @ApiIgnore User currentUser) throws Exception{
        UserInfoFields userInfoFields = infoFieldsPopulator.populate(currentUser);
        return new ResponseEntity<>(userInfoFields, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/users", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 200, response = UserInfoFields.class, message = "Create a user")
    })
    public ResponseEntity<?> post(@RequestBody @Valid CreateUserForm createUserForm) throws Exception{

        User user = createUserFormPopulator.populate(createUserForm);

        //TODO check duplicate

        user = userService.create(user);

        UserInfoFields userInfoFields = infoFieldsPopulator.populate(user);

        return new ResponseEntity<>(userInfoFields, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/user", method = RequestMethod.PATCH)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = UserInfoFields.class, message = "Update user")
    })
    public ResponseEntity<?> patch(@CurrentUser @ApiIgnore User currentUser, @RequestBody UpdateUserForm updateUserForm) throws Exception{

        currentUser = updateUserFormPopulator.populate(updateUserForm, currentUser);
        currentUser = userService.save(currentUser);

        UserInfoFields userInfoFields = infoFieldsPopulator.populate(currentUser);
        return new ResponseEntity<>(userInfoFields, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/user", method = RequestMethod.DELETE)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = UserInfoFields.class, message = "Delete user")
    })
    public ResponseEntity<?> delete(@CurrentUser @ApiIgnore User currentUser) throws Exception{

        userService.delete(currentUser);
        UserInfoFields userInfoFields = infoFieldsPopulator.populate(currentUser);

        return new ResponseEntity<>(userInfoFields, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/user/installation", method = RequestMethod.POST)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = InstallationModel.class, message = "Update installation id")
    })
    public ResponseEntity<?> postInstallationId(@RequestBody String installationId,
                                                @CurrentUser @ApiIgnore User currentUser) throws Exception{

        InstallationModel installation = installationManager.save(currentUser.getId(), installationId);
        return new ResponseEntity<>(installation, HttpStatus.OK);
    }
}
