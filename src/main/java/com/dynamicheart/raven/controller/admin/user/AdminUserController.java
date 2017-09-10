package com.dynamicheart.raven.controller.admin.user;

import com.dynamicheart.raven.authorization.annotation.AdminAuthorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.authorization.manager.TokenManager;
import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.controller.admin.user.field.UserDetailForm;
import com.dynamicheart.raven.controller.admin.user.populator.UserDetailFormPopulator;
import com.dynamicheart.raven.controller.app.user.field.UserInfoFields;
import com.dynamicheart.raven.controller.admin.user.field.UserBriefInfo;
import com.dynamicheart.raven.controller.app.user.populator.UserInfoFieldsPopulator;
import com.dynamicheart.raven.controller.admin.user.populator.UserBriefInfoPopulator;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.user.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;
import java.util.List;


//TODO:authorization

@RestController
@RequestMapping("/admin/api/v1/users")
public class AdminUserController {
    @Inject
    private UserService userService;

    @Inject
    private TokenManager tokenManager;

    @Inject
    private UserBriefInfoPopulator userBriefInfoPopulator;

    @Inject
    private UserInfoFieldsPopulator userInfoFieldsPopulator;

    @Inject
    private UserDetailFormPopulator userDetailFormPopulator;

    @RequestMapping(value = "searchInfoByName/{username}", method = RequestMethod.GET)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = UserBriefInfo.class, message = "query user info by username")
    })
    ResponseEntity<?> getByName(@PathVariable String username) throws Exception{
        List<User> userList=userService.getBySimilarName(username);
        List<UserBriefInfo> userBriefInfoList=userBriefInfoPopulator.populateList(userList);
        return new ResponseEntity<>(userBriefInfoList, HttpStatus.OK);
    }

    @RequestMapping(value = "allUserInfo", method = RequestMethod.GET)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = "query all user infos")
    })
    ResponseEntity<?> getAll() throws Exception{
        List<User> userList=userService.getAll();
        List<UserBriefInfo> userBriefInfoList=userBriefInfoPopulator.populateList(userList);
        return new ResponseEntity<>(userBriefInfoList, HttpStatus.OK);
    }




    @RequestMapping(value = "searchDetailById/{id}", method = RequestMethod.GET)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = UserDetailForm.class, message = "query user detail by id")
    })
    ResponseEntity<?> getDById(@PathVariable String id) throws Exception{
        User user=userService.getById(id);

        UserDetailForm userDetailForm=userDetailFormPopulator.populate(user);
        return new ResponseEntity<>(userDetailForm, HttpStatus.OK);
    }



    @RequestMapping(value = "grantPrivilege/{id}", method = RequestMethod.PUT)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = UserDetailForm.class, message = "promote user Privilege")
    })
    ResponseEntity<?> grantPrivilege(@PathVariable String id)throws Exception{
        if(userService.exists(id)==false)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        User user=userService.getById(id);
        user.setAdmin(true);
        userService.update(user);

        UserDetailForm userDetailForm=userDetailFormPopulator.populate(user);
        return new ResponseEntity<>(userDetailForm, HttpStatus.OK);
    }

    @RequestMapping(value = "revokePrivilege/{id}", method = RequestMethod.PUT)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = UserDetailForm.class, message = "reduce user Privilege")
    })
    ResponseEntity<?> revokePrivilege(@PathVariable String id)throws Exception{
        if(userService.exists(id)==false)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        User user=userService.getById(id);
        user.setAdmin(false);
        userService.update(user);

        UserDetailForm userDetailForm=userDetailFormPopulator.populate(user);
        return new ResponseEntity<>(userDetailForm, HttpStatus.OK);
    }

    @RequestMapping(value = "disableUser/{id}", method = RequestMethod.PUT)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = UserDetailForm.class, message = "disable user")
    })
    ResponseEntity<?> disableUser(@CurrentUser @ApiIgnore User currentUser, @PathVariable String id)throws Exception{
        if(userService.exists(id)==false)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        if(currentUser.getId().equals(id))
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);

        User user=userService.getById(id);
        user.setStatus(Constants.USER_STATUS_DISABLE);
        userService.update(user);

        UserDetailForm userDetailForm=userDetailFormPopulator.populate(user);
        return new ResponseEntity<>(userDetailForm, HttpStatus.OK);
    }

    @RequestMapping(value = "activateUser/{id}", method = RequestMethod.PUT)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = UserDetailForm.class, message = "activate user")
    })
    ResponseEntity<?> activateUser(@PathVariable String id)throws Exception{
        if(userService.exists(id)==false)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        User user=userService.getById(id);
        user.setStatus(Constants.USER_STATUS_OK);
        userService.update(user);

        UserDetailForm userDetailForm=userDetailFormPopulator.populate(user);
        return new ResponseEntity<>(userDetailForm, HttpStatus.OK);
    }

    @RequestMapping(value = "censorAvatar/{id}", method = RequestMethod.PUT)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = UserDetailForm.class, message = "delete user avatar")
    })
    ResponseEntity<?> censorAvatar(@PathVariable String id)throws Exception{
        if(userService.exists(id)==false)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        User user=userService.getById(id);
        user.setAvatar(null);
        userService.update(user);

        UserDetailForm userDetailForm=userDetailFormPopulator.populate(user);
        return new ResponseEntity<>(userDetailForm, HttpStatus.OK);    }



    @RequestMapping(value = "deleteUser/{id}", method = RequestMethod.DELETE)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = UserDetailForm.class, message = "delete user")
    })
    ResponseEntity<?> deleteUser(@PathVariable String id)throws Exception{
        if(userService.exists(id)==false)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        User user=userService.getById(id);
        userService.delete(user);

        UserDetailForm userDetailForm=userDetailFormPopulator.populate(user);
        return new ResponseEntity<>(userDetailForm, HttpStatus.OK);
    }

}
