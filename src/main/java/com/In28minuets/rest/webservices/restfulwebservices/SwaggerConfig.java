package com.In28minuets.rest.webservices.restfulwebservices;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Configuartion
//Enable Swagger
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //Bean -Docket
    //swagger 2

    public final static Contact DEFAULT_CONTACT= new Contact("Aravind Reddy","https://123.com","aravindreddy.vbit@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Awesome API Title","Awesome API Documentation","1.0",
            "urm:tos",DEFAULT_CONTACT,
            "Apache 2.0","http://www.apache",Arrays.asList()
    );
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES=new HashSet<>(Arrays.asList("application/xml","application/json"));

    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }



}
