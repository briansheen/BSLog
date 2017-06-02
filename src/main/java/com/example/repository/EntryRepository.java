package com.example.repository;

import com.example.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bsheen on 6/2/17.
 */

@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {
}
