package com.atable.alcoholknowledge.repository;

import com.atable.alcoholknowledge.model.CorkageInfo;

import java.util.List;
import java.util.Optional;

public interface CorkageInfoRepository {
    CorkageInfo save(CorkageInfo cInfo);
    void checkAsCorkageStore(Long id);
    Optional<CorkageInfo> findById(Long id);
    Long findByAddr(String Address);
    List<CorkageInfo> findAll();
}
