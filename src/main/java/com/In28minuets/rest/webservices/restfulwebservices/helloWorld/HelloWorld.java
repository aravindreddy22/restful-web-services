package com.In28minuets.rest.webservices.restfulwebservices.helloWorld;

import com.In28minuets.rest.webservices.restfulwebservices.helloWorld.HelloWorldBean;
import com.In28minuets.rest.webservices.restfulwebservices.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class HelloWorld {

    @Autowired
   private MessageSource messageSource;

    //@RequestMapping(path = "/hello-world",method = RequestMethod.GET)
    @GetMapping(path="/hello-world")
    public String helloWorld(){
        return "Hello World!";
    }
    //hello-world bean
    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("HelloWorldBean");
    }

    //hello-world bean with path param
    @GetMapping(path="/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable("name") String name){
        return new HelloWorldBean(String.format("Hello World %s",name));
    }

  /*  @GetMapping(path="/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name="Accept-Language",required = false) Locale locale){
        return messageSource.getMessage("good.morning.message",null,locale);
    }*/

    @GetMapping(path="/hello-world-internationalized")
    public String helloWorldInternationalized(){
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }
    
    @PostMapping(value="/test")
    public void postMethod (@RequestBody String input ) {
        //TODSomeEnityDataOentity: process POST request
        System.out.println(input);
    }
    
}
