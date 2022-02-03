package com.atable.alcoholknowledge.service;

import com.atable.alcoholknowledge.model.CorkageStore;
import com.atable.alcoholknowledge.repository.MemoryCkStoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CorkageStoreServiceTest {

    CorkageStoreService ckStoreService;
    MemoryCkStoreRepository ckStoreRepository;

    @BeforeEach
    void beforeEach() {
        ckStoreRepository = new MemoryCkStoreRepository();
        ckStoreService = new CorkageStoreService(ckStoreRepository);
    }

    @AfterEach
    void afterEach() {
        ckStoreRepository.clearStore();
    }
    @Test
    void register() {
        // given
        CorkageStore ckStore = new CorkageStore();
        ckStore.setAddr("address");

        // when
        Long id = ckStoreService.register(ckStore);

        // then
        CorkageStore findCkInfo = ckStoreService.findOne(id).get();
        assertThat(ckStore.getId()).isEqualTo(findCkInfo.getId());
    }

    @Test
    void findOneByAddr() {
        // given
        CorkageStore ckStore1 = new CorkageStore();
        ckStore1.setAddr("address 1");
        ckStoreService.register(ckStore1);

        CorkageStore ckStore2 = new CorkageStore();
        ckStore2.setAddr("address 2");
        ckStoreService.register(ckStore2);

        CorkageStore result = ckStoreService.findOneByAddr("address 1").get();
        assertThat(result).isEqualTo(ckStore1);
    }

    @Test
    void findCkStores() {
        CorkageStore ckStore1 = new CorkageStore();
        ckStore1.setAddr("address 1");
        ckStoreService.register(ckStore1);

        CorkageStore ckStore2 = new CorkageStore();
        ckStore2.setAddr("address 2");
        ckStoreService.register(ckStore2);

        List<CorkageStore> result = ckStoreService.findCkStores();
        assertThat(result.size()).isEqualTo(2);
    }
}