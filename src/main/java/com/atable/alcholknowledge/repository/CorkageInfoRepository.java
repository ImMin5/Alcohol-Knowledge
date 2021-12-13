package com.atable.alcholknowledge.repository;

import com.atable.alcholknowledge.model.CorkageInfo;

import java.util.List;
import java.util.Optional;

public interface CorkageInfoRepository {
    CorkageInfo save(CorkageInfo cInfo);
    Optional<CorkageInfo> findById(Long id);
    List<CorkageInfo> findAll();
}
