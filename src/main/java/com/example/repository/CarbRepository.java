package com.example.repository;

import com.example.domain.Carb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bsheen on 6/5/17.
 */

@Repository
public interface CarbRepository extends JpaRepository<Carb, Integer> {
}
