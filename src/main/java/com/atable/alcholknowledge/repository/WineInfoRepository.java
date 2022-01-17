package com.atable.alcholknowledge.repository;
import com.atable.alcholknowledge.dto.WineInfoDto;
import com.atable.alcholknowledge.model.WineInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.TypedQuery;
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
