package com.atable.alcholknowledge.repository;

import com.atable.alcholknowledge.model.WineInfo;

import java.util.List;
import java.util.Optional;

public interface WineInfoRepository {
    WineInfo save(WineInfo wineInfo);
    Optional<WineInfo> findById(Long id);
    List<WineInfo> findByNameEng(String nameEng);
    List<WineInfo> findByNameKor(String nameKor);
    List<WineInfo> findAll();
}
