package com.atable.alcholknowledge.service;

import com.atable.alcholknowledge.model.CorkageStore;
import com.atable.alcholknowledge.repository.CorkageStoreRepository;
import com.atable.alcholknowledge.repository.MemoryCkStoreRepository;

import java.util.List;
import java.util.Optional;

public class CorkageStoreService {
    private final CorkageStoreRepository ckStoreRepository;

    public CorkageStoreService(CorkageStoreRepository ckStoreRepository) {
        this.ckStoreRepository = ckStoreRepository;

    }

    public Long register(CorkageStore ckStore) {
        validateDuplicateStore(ckStore);
        ckStoreRepository.save(ckStore);

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
    public List<CorkageStore> findCkStores() {
        return ckStoreRepository.findAll();
    }
}
