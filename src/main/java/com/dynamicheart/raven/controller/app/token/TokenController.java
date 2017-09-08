package com.dynamicheart.raven.controller.app.token;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.authorization.manager.TokenManger;
import com.dynamicheart.raven.authorization.model.TokenModel;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.repositories.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by dynamicheart on 21/8/2017.
 */
@RestController
@RequestMapping("/api/v1/tokens")
public class TokenController {

    @Inject
    private UserRepository userRepository;

    @Inject
    private TokenManger tokenManager;

    @Inject
    private PasswordEncoder encoder;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Assert.notNull(username, "username can not be empty");
        Assert.notNull(password, "password can not be empty");
        User user = userRepository.findUserByUsername(username);
        if (user == null || !encoder.matches(password, user.getPassword())) {
            return ResponseEntity.notFound().build();
        }

        TokenModel token = tokenManager.createToken(user.getId());
        return new ResponseEntity<TokenModel>(token, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Authorization
    public ResponseEntity logout(@CurrentUser User user) {
        tokenManager.deleteToken(user.getId());
        return new ResponseEntity(HttpStatus.OK);
    }
}
