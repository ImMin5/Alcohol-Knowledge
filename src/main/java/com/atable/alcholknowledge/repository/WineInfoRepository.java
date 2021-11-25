package com.atable.alcholknowledge.repository;

import com.atable.alcholknowledge.model.WineInfo;

import java.util.List;
import java.util.Optional;

public interface WineInfoRepository {
    WineInfo save(WineInfo wineinfo);
    Optional<WineInfo> findById(Long id);
    Optional<WineInfo> findByNameEng(String nameEng);
    List<WineInfo> findAll();
}
