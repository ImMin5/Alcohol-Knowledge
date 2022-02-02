package com.atable.alcoholknowledge.repository;

import com.atable.alcoholknowledge.model.CorkageStore;

import java.util.List;
import java.util.Optional;

public interface CorkageStoreRepository {
    CorkageStore save(CorkageStore cStore);
    Optional<CorkageStore> findById(Long id);
    Optional<CorkageStore> findByName(String name);
    Optional<CorkageStore> findByAddr(String addr);
    List findByKeyword(String keyword);
    List findPageByKeyword(String keyword, int index, int pageSize);
    List<CorkageStore> findAll();
    List<CorkageStore> findPage(int index, int pageSize);
}
