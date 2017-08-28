package com.dynamicheart.raven.controller.token;

import com.dynamicheart.raven.authorization.manager.TokenManger;
import com.dynamicheart.raven.repositories.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by dynamicheart on 21/8/2017.
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Inject
    private UserRepository userRepository;

    @Inject
    private TokenManger tokenManger;

    public ResponseEntity<Void> login(@RequestParam String username, @RequestParam String password){
        return null;
    }


}
