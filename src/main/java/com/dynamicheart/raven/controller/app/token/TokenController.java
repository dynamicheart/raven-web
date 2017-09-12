package com.dynamicheart.raven.controller.app.token;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.authorization.manager.TokenManager;
import com.dynamicheart.raven.authorization.model.AuthenticationModel;
import com.dynamicheart.raven.authorization.model.TokenModel;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.app.token.field.LoginForm;
import com.dynamicheart.raven.controller.common.model.GenericResponseBody;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.repositories.user.UserRepository;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by dynamicheart on 21/8/2017.
 */
@RestController
@RequestMapping("/api/v1/tokens")
public class TokenController {

    @Inject
    private UserRepository userRepository;

    @Inject
    private TokenManager tokenManager;

    @Inject
    private PasswordEncoder encoder;

    @RequestMapping(method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 200, response = AuthenticationModel.class, message = "Login")
    })
    public ResponseEntity<?> login(@RequestBody @Valid LoginForm loginForm) {
        User user = userRepository.findTopByUsername(loginForm.getUsername());
        if (user == null || !encoder.matches(loginForm.getUsername(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        TokenModel token = tokenManager.createToken(user.getId());

        AuthenticationModel auth = new AuthenticationModel(token.getUserId(),token.toBase64());

        return new ResponseEntity<>(auth, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Authorization
    public ResponseEntity logout(@CurrentUser @ApiIgnore User user) {
        tokenManager.deleteToken(user.getId());
        return new ResponseEntity(HttpStatus.OK);
    }
}
