package com.example.service;

import com.example.domain.Entry;
import com.example.domain.User;

import java.util.List;

/**
 * Created by bsheen on 6/2/17.
 */
public interface UserService {

    public List<User> findAll();

    public User findByUsername(String username);

    public User addUser(User user);

    public List<Entry> getEntriesByUser(String username);

    public List<Entry> getEntriesByUserToday(String username);
}
