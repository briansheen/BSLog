package com.example.service;


import com.example.domain.Carb;

import com.example.domain.AuthCompKey;
import com.example.domain.Authorities;

import com.example.domain.Entry;
import com.example.domain.User;
import com.example.repository.AuthoritiesRespository;
import com.example.repository.EntryRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionScoped;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import java.time.LocalDateTime;

import java.util.List;

/**
 * Created by bsheen on 6/2/17.
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthoritiesRespository authoritiesRespository;

    @Autowired
    EntryRepository entryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        User user = userRepository.findOne(username);
        if(user != null){
            //have to touch entry to get lazyloading to load
            if(user.getEntries()!=null){
                user.getEntries().size();
                for (Entry entry : user.getEntries()) {
                    entry.getCarbs().size();
                }
            }
        }
        return user;
    }

    @Override
    @Transactional
    public User addUser(String username) {
        User user = new User();
        user.setUsername(username);
        user.setEnabled(true);
        LocalDateTime ldt = LocalDateTime.now();
        user.setCreatedAt(ldt);
        user = userRepository.save(user);

        Authorities authorities = new Authorities();
        AuthCompKey authCompKey = new AuthCompKey();
        authCompKey.setAuthority("ROLE_USER");
        authCompKey.setUsername(username);
        authorities.setCompKey(authCompKey);
        authoritiesRespository.save(authorities);

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entry> findAllEntriesByUser(String username) {
        User user = userRepository.findOne(username);
        if(user.getEntries()!=null) {
            return user.getEntries();
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entry> getEntriesByUserToday(String username) {
        List<Entry> todaysEntries = new ArrayList<>();
        List<Entry> entries = findAllEntriesByUser(username);
        if(entries != null) {
            for (Entry entry : entries) {
                if (entry.getDate().equals(LocalDate.now(ZoneId.of("America/Chicago")))) {
                    todaysEntries.add(entry);
                }
            }
        }
        return todaysEntries;
    }

    @Override
    @Transactional
    public User updateUserSettings(User user) {
        return userRepository.save(user);
    }
}
