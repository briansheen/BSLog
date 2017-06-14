package com.example.repository;

import com.example.domain.AuthCompKey;
import com.example.domain.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bsheen on 6/14/17.
 */
public interface AuthoritiesRespository extends JpaRepository<Authorities, AuthCompKey> {
}
