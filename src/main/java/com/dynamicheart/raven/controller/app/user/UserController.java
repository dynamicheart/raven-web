package com.dynamicheart.raven.controller.app.user;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.controller.app.user.field.CreateUserForm;
import com.dynamicheart.raven.controller.app.user.field.UpdateUserForm;
import com.dynamicheart.raven.controller.app.user.field.UserInfoFields;
import com.dynamicheart.raven.controller.app.user.populator.UserInfoFieldsPopulator;
import com.dynamicheart.raven.model.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Inject
    private UserInfoFieldsPopulator infoFieldsPopulator;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Authorization
    ResponseEntity<UserInfoFields> get(@PathVariable String id, @CurrentUser User user, HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(!id.equals(user.getId())){
            return new ResponseEntity<UserInfoFields>(HttpStatus.FORBIDDEN);
        }

        UserInfoFields userInfoFields = infoFieldsPopulator.populate(user);

        return new ResponseEntity<UserInfoFields>(userInfoFields, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<UserInfoFields> post(@RequestBody CreateUserForm createUserForm){

        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Authorization
    ResponseEntity<UserInfoFields> put(@PathVariable String id, @CurrentUser User user, @RequestBody UpdateUserForm updateUserForm){
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Authorization
    ResponseEntity<UserInfoFields> delete(@PathVariable String id, @CurrentUser User user){
        return null;
    }
}
