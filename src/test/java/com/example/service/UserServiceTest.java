package com.example.service;

import com.example.domain.Carb;
import com.example.domain.Entry;
import com.example.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bsheen on 6/2/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    EntryService entryService;

    @Autowired
    CarbService carbService;

    @Test
    public void getUsers(){
        List<User> users = userService.findAll();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size()>0);
        for(User user : users){
            System.out.println("\n\n\n"+ userService.findByUsername(user.getUsername()));
        }
    }

    @Test
    public void addUser(){
        User user = new User();
        user.setCreatedAt(LocalDateTime.now());
        user.setEnabled(true);
        String username = Long.toString(System.currentTimeMillis());
        user.setUsername(username);
        user.setPassword("password");
        userService.addUser(user);
        User user2 = userService.findByUsername(username);
        Assert.assertNotNull(user2);
    }

    @Test
    public void addEntry(){
        User user = new User();
        user.setCreatedAt(LocalDateTime.now());
        user.setEnabled(true);
        String username = Long.toString(System.currentTimeMillis());
        user.setUsername(username);
        user.setPassword("password");
        userService.addUser(user);

        Entry entry = new Entry();
        LocalDateTime localDateTime = LocalDateTime.now();
        entry.setDateTime(localDateTime);
        entry.setUser(user);
        entryService.addEntry(entry);

        User user2 = userService.findByUsername(username);

        Assert.assertNotNull(user2.getEntries());
        Assert.assertTrue(user2.getEntries().size() == 1);

    }

    @Test
    public void addCarb(){
        User user = new User();
        user.setCreatedAt(LocalDateTime.now());
        user.setEnabled(true);
        String username = Long.toString(System.currentTimeMillis());
        user.setUsername(username);
        user.setPassword("password");
        userService.addUser(user);

        Entry entry = new Entry();
        LocalDateTime localDateTime = LocalDateTime.now();
        entry.setDateTime(localDateTime);
        entry.setUser(user);
        entryService.addEntry(entry);

        System.out.println(entry);

        Carb carb = new Carb();
        carb.setTotalCarbs(15);
        carb.setEntry(entry);
        carbService.addCarb(carb);

        Carb carb2 = new Carb();
        carb2.setTotalCarbs(23);
        carb2.setEntry(entry);
        carbService.addCarb(carb2);

        entryService.updateEntry(entry);

        User user2 = userService.findByUsername(username);

        Assert.assertNotNull(user2.getEntries().get(0).getCarbs());
        Assert.assertTrue(user2.getEntries().get(0).getCarbs().size()==2);
    }

    @Test
    public void addBloodSugar(){
        User user = new User();
        user.setCreatedAt(LocalDateTime.now());
        user.setEnabled(true);
        String username = Long.toString(System.currentTimeMillis());
        user.setUsername(username);
        user.setPassword("password");
        userService.addUser(user);

        Entry entry = new Entry();
        LocalDateTime localDateTime = LocalDateTime.now();
        entry.setDateTime(localDateTime);
        entry.setUser(user);
        entryService.addEntry(entry);

        User user2 = userService.findByUsername(username);

        //add bloodsugar stuff
    }

}
