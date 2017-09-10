package com.dynamicheart.raven.authorization.resolvers;

import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.repositories.user.UserRepository;
import com.dynamicheart.raven.services.user.UserService;
import com.dynamicheart.raven.utils.exception.ServiceException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.inject.Inject;

@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Inject
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //return true if parameter type is user and annotated with CurrentUser
        return parameter.getParameterType().isAssignableFrom(User.class) && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //get user id that stored at token checking
        String currentUserId = (String) webRequest.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
        if (currentUserId != null){
            // find user from database and return it
            User user = userService.getById(currentUserId);
            if(user == null){
                throw new ServiceException();
            }
            return user;
        }
        throw new MissingServletRequestPartException(Constants.CURRENT_USER_ID);
    }
}
