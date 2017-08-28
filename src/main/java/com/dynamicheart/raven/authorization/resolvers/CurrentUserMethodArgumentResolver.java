package com.dynamicheart.raven.authorization.resolvers;

import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.constants.Constants;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.repositories.user.UserRepository;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.inject.Inject;

/**
 * Created by dynamicheart on 21/8/2017.
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Inject
    private UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //return true if parameter type is user and annotated with CurrentUser
        return parameter.getParameterType().isAssignableFrom(User.class) && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Long currentUserId = (Long) webRequest.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
        if (currentUserId != null){
            return userRepository.findOne(currentUserId);
        }
        throw new MissingServletRequestPartException(Constants.CURRENT_USER_ID);
    }
}
