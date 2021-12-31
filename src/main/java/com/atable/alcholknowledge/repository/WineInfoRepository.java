package com.atable.alcholknowledge.repository;

import com.atable.alcholknowledge.dto.WineInfoDto;
import com.atable.alcholknowledge.model.WineInfo;

import java.util.List;
import java.util.Optional;

public interface WineInfoRepository {
    WineInfo save(WineInfo wineInfo);
    WineInfo findById(Long id);
    List<WineInfo> findByWord(String word);
    List<WineInfo> findAll();
}
