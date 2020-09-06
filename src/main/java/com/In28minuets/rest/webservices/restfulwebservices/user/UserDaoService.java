package com.In28minuets.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users= new ArrayList<>();
    private static int userCount;

    static{
        users.add(new User(1,"Aravind",new Date()));
        users.add(new User(2,"Sanju",new Date()));
        users.add(new User(3,"Karthik",new Date()));
        userCount=3;
    }

    public List<User> findall(){
        return users;
    }

    public User save(User user){
        if(user.getId()==null)
            user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User  findOne(int id){
        for (User user:users){
            if (user.getId() ==id)
                return user;
        }
            return null;
    }

    public User  deleteById(int id){

      /*  User user= new User();
        user.m1();
        user.m3();
        user.m4();
        user.m2();
*/
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user= iterator.next();
            if(user.getId()==id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }
}
