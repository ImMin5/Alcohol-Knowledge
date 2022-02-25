package com.atable.alcoholknowledge.service;

import com.atable.alcoholknowledge.model.CorkageStore;
import com.atable.alcoholknowledge.repository.CorkageInfoRepository;
import com.atable.alcoholknowledge.repository.CorkageStoreRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class CorkageStoreService {
    private final CorkageInfoRepository ckInfoRepository;
    private final CorkageStoreRepository ckStoreRepository;

    public CorkageStoreService(CorkageInfoRepository ckInfoRepository, CorkageStoreRepository ckStoreRepository) {
        this.ckStoreRepository = ckStoreRepository;
        this.ckInfoRepository = ckInfoRepository;
    }

    public Long register(CorkageStore ckStore) {
        try{
            validateDuplicateStore(ckStore);
            ckStoreRepository.save(ckStore);
        } catch (IllegalStateException e) {
            Long ckInfoId = ckInfoRepository.findByAddr(ckStore.getAddr());
            ckInfoRepository.checkAsCorkageStore(ckInfoId);
        }

        return ckStore.getId();
    }

    private void validateDuplicateStore(CorkageStore ckStore) {
        ckStoreRepository.findByAddr(ckStore.getAddr())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 등록된 콜키지 매장입니다");
                });
    }

    public Optional<CorkageStore> findOneByAddr(String addr) {
        return ckStoreRepository.findByAddr(addr);
    }

    public Optional<CorkageStore> findOne(Long id) {
        return ckStoreRepository.findById(id);
    }

    public List<CorkageStore> findByKeyword(String keyword) {
        String word = "%" + keyword + "%";
        return ckStoreRepository.findByKeyword(word);
    }

    public List<CorkageStore> findByKeyWord(String keyword, int pageIndex, int pageSize) {
        String word = "%" + keyword + "%";
        return ckStoreRepository.findPageByKeyword(word, pageIndex, pageSize);
    }

    public List<CorkageStore> findCkStores() {
        return ckStoreRepository.findAll();
    }

    public List<CorkageStore> findCkStores(int pageIndex, int pageSize) {
        return ckStoreRepository.findPage(pageIndex, pageSize);
    }
}
