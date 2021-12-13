package com.atable.alcholknowledge.repository;

import com.atable.alcholknowledge.model.CorkageStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CorkageStoreRepository {
    CorkageStore save(CorkageStore cStore);
    Optional<CorkageStore> findById(Long id);
    Optional<CorkageStore> findByName(String name);
    Optional<CorkageStore> findByAddr(String addr);
    List<CorkageStore> findAll();
}
