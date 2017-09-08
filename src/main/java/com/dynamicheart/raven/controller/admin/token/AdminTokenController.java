package com.dynamicheart.raven.controller.admin.token;

import com.dynamicheart.raven.authorization.annotation.AdminAuthorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.authorization.manager.TokenManager;
import com.dynamicheart.raven.authorization.model.TokenModel;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.common.model.GenericResponseBody;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.repositories.user.UserRepository;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;

@RestController
@RequestMapping("/admin/api/v1/tokens")
public class AdminTokenController {

    @Inject
    private UserRepository userRepository;

    @Inject
    private TokenManager tokenManager;

    @Inject
    private PasswordEncoder encoder;

    @RequestMapping(method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 200, response = TokenModel.class, message = "Admin Login")
    })
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Assert.notNull(username, Message.MESSAGE_USERNAME_NOT_EMPTY);
        Assert.notNull(password, Message.MESSAGE_PASSWORD_NOT_EMPTY);
        User user = userRepository.findUserByUsername(username);
        if (user == null || !encoder.matches(password, user.getPassword())) {
            return ResponseEntity.notFound().build();
        }

        if(!user.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        TokenModel token = tokenManager.createAdminToken(user.getId());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @AdminAuthorization
    public ResponseEntity logout(@CurrentUser @ApiIgnore User user) {
        tokenManager.deleteAdminToken(user.getId());
        return new ResponseEntity(HttpStatus.OK);
    }
}