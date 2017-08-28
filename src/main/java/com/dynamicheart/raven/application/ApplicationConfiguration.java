package com.dynamicheart.raven.application;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by dynamicheart on 6/8/2017.
 */
@Configuration
@ComponentScan({"com.dynamicheart.raven"})
@EnableAutoConfiguration
@ImportResource("classpath:/spring/application-context.xml")
public class ApplicationConfiguration {


}
