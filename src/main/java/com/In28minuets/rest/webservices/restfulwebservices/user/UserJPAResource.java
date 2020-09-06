package com.In28minuets.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
public class UserJPAResource {

    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    //retrieve all users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){

        return userRepository.findAll();
    }

    //retrieve User(int id)
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable("id") Integer id){
        Optional<User> user = userRepository.findById(id);

       // User user= optionalUser.get();
        if(!user.isPresent()) {
            throw new UserNotFoundExeception("id -" + id);
        }

        //all Resources
        EntityModel<User> resource= EntityModel.of(user.get());

        WebMvcLinkBuilder linkTo= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));
        return resource;
    }


    //input- details of the user.
    //output-  created and Return the created URI.
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        
        User savedUser=userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        //return ResponseEntity.ok().build();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUSer(@PathVariable("id") Integer id){
       userRepository.deleteById(id);

    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retriveAllPosts(@PathVariable("id") int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundExeception("id- "+id);
        List<Post> posts=user.get().getPost();
        return posts;
    }

    @PostMapping("/jpa/users/{id}/post")
    public ResponseEntity<Object> createPost(@PathVariable("id") int id, @RequestBody() Post post){

        Optional<User> userOptinal = userRepository.findById(id);
        if(!userOptinal.isPresent())
            throw new UserNotFoundExeception("id- "+id);

        User user = userOptinal.get();
        post.setUser(user);
        postRepository.save(post);

        User savedUser=userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();

        //return ResponseEntity.ok().build();
        return ResponseEntity.created(location).build();

    }
}
