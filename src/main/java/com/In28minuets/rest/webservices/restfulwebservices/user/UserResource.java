package com.In28minuets.rest.webservices.restfulwebservices.user;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    //retrieve all users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findall();
    }

    //retrieve User(int id)
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable("id") int id){
        User user = userDaoService.findOne(id);
        if(user==null) {
            throw new UserNotFoundExeception("id -" + id);
        }

        //all Resources
        EntityModel<User> resource= EntityModel.of(user);
        WebMvcLinkBuilder linkTo= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }


    //input- details of the user.
    //output-  created and Return the created URI.
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        
        User savedUser=userDaoService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        //return ResponseEntity.ok().build();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/users/{id}")
    public void deleteUSer(@PathVariable("id") int id){
        User deletedUser=userDaoService.deleteById(id);
        if(deletedUser==null) {
            throw new UserNotFoundExeception("id -" + id);
        }

    }
}
