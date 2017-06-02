package com.example.service;

import com.example.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bsheen on 6/2/17.
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<com.example.domain.User> findAll() {
        return userRepository.findAll();
    }
}
