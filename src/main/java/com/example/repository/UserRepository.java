package com.example.repository;

import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bsheen on 6/2/17.
 */

@Repository
public interface UserRepository extends JpaRepository<User, String>{
}
