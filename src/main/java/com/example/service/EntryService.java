package com.example.service;

import com.example.domain.Entry;
import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by bsheen on 6/5/17.
 */
public interface EntryService {
    public List<Entry> findAll();

    public Entry addEntry(Entry entry);

    public Entry findEntry(Integer eid);

    public Entry updateEntry(Entry entry, Integer eid);

    public void deleteEntry(Integer eid);

    public void deleteEntries(List<Integer> eids);

    public Entry updateEntryTotCarbs(Integer eid);

    public Entry getNewEntry();

}
