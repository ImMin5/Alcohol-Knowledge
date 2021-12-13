package com.atable.alcholknowledge.service;

import com.atable.alcholknowledge.model.CorkageInfo;
import com.atable.alcholknowledge.repository.CorkageInfoRepository;
import com.atable.alcholknowledge.repository.CorkageStoreRepository;
import com.atable.alcholknowledge.repository.MemoryCkInfoRepository;
import com.atable.alcholknowledge.repository.MemoryCkStoreRepository;

import java.util.List;
import java.util.Optional;

public class CorkageInfoService {
    private final CorkageInfoRepository ckInfoRepository;
    private final CorkageStoreRepository ckStoreRepository;

    public CorkageInfoService(CorkageInfoRepository ckInfoRepository, CorkageStoreRepository ckStoreRepository) {
        this.ckInfoRepository = ckInfoRepository;
        this.ckStoreRepository = ckStoreRepository;
    }

    /*
        콜키지 매장 접수
         */
    public Long register(CorkageInfo ckInfo) {
        validateDuplicateStore(ckInfo);
        ckInfoRepository.save(ckInfo);
        return ckInfo.getId();
    }

    private void validateDuplicateStore(CorkageInfo ckInfo) {
        ckStoreRepository.findByAddr(ckInfo.getAddr())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 접수 요청된 장소입니다.");
                        });
    }

    /*
    전체 접수된 매장 조회
     */
    public List<CorkageInfo> findCkInfos() {
        return ckInfoRepository.findAll();
    }

    public Optional<CorkageInfo> findOne(Long ckId) {
        return ckInfoRepository.findById(ckId);
    }
}
