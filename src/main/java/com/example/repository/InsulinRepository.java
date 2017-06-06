package com.example.repository;

import com.example.domain.Insulin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bsheen on 6/6/17.
 */

@Repository
public interface InsulinRepository extends JpaRepository<Insulin, Integer> {
}
