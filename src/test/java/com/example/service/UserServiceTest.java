package com.example.service;

import com.example.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by bsheen on 6/2/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void getUsers(){
        List<User> users = userService.findAll();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size()>0);
        for(User user : users){
            System.out.println("\n\n\n"+ userService.findById(user.getUsername()));
        }
    }

}
