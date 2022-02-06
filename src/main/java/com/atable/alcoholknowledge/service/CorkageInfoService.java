package com.atable.alcoholknowledge.service;

import com.atable.alcoholknowledge.model.CorkageInfo;
import com.atable.alcoholknowledge.repository.CorkageInfoRepository;
import com.atable.alcoholknowledge.repository.CorkageStoreRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
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
        if (!validateDuplicateStore(ckInfo)) {
            return -1L;
        }
        ckInfoRepository.save(ckInfo);
        return ckInfo.getId();
    }

    private boolean validateDuplicateStore(CorkageInfo ckInfo) {
        if (ckStoreRepository.findByAddr(ckInfo.getAddr()).isPresent())
            return false;
        return true;
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
