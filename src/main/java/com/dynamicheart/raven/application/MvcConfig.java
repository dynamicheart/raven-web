package com.dynamicheart.raven.application;

import com.dynamicheart.raven.authorization.interceptor.AdminAuthorizationInterceptor;
import com.dynamicheart.raven.authorization.interceptor.AuthorizationInterceptor;
import com.dynamicheart.raven.authorization.resolvers.CurrentUserMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.inject.Inject;
import java.util.List;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{

    @Inject
    private AuthorizationInterceptor authorizationInterceptor;

    @Inject
    private AdminAuthorizationInterceptor adminAuthorizationInterceptor;

    @Inject
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
        registry.addInterceptor(adminAuthorizationInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
    }
}
