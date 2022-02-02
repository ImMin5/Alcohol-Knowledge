package com.atable.alcoholknowledge.repository;
import com.atable.alcoholknowledge.model.WineInfo;

import java.util.List;

public interface WineInfoRepository {
    WineInfo save(WineInfo wineInfo);
    WineInfo findById(Long id);
    List<WineInfo> findByWord(String word);
    List<WineInfo> findAll();
    Integer delete(Long pk);
    List<WineInfo> findPagination(int index, int pageSize);
    List<WineInfo> findByWordPagination(int index, int pageSize, String word);
}
