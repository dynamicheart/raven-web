package com.dynamicheart.raven.authorization.interceptor;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.manager.TokenManager;
import com.dynamicheart.raven.authorization.model.TokenModel;
import com.dynamicheart.raven.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by dynamicheart on 21/8/2017.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Inject
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("preHandling");

        //pass if not reflected to methods
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //get token from request header
        String authorization = request.getHeader(Constants.HEADER_X_AUTH);

        //check token
        TokenModel model = tokenManager.getToken(authorization);
        if(tokenManager.checkToken(model)){
            request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
            return true;
        }

        //if checkToken fails and the method was annotated with Authorization return 401
        if(method.getAnnotation(Authorization.class) != null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }
}
