package com.example.service;

import com.example.domain.Entry;
import com.example.domain.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionScoped;
import java.util.List;

/**
 * Created by bsheen on 6/2/17.
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        User user = userRepository.findOne(username);
        //have to touch entry to get lazyloading to load
        user.getEntries().size();
        for (Entry entry : user.getEntries()) {
            entry.getCarbs().size();
        }
        return user;
    }

    @Override
    @Transactional
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
