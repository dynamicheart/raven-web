package com.dynamicheart.raven.application;

import com.dynamicheart.raven.utils.LoggingRequestInterceptor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan({"com.dynamicheart.raven"})
@EnableAutoConfiguration
@EnableMongoAuditing
@ImportResource("classpath:/spring/application-context.xml")
public class ApplicationConfiguration {

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder){
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new LoggingRequestInterceptor());

        return builder
                .interceptors(interceptors)
                .build();
    }
}
