package com.atable.alcholknowledge.service;

import com.atable.alcholknowledge.model.CorkageInfo;
import com.atable.alcholknowledge.model.CorkageStore;
import com.atable.alcholknowledge.repository.CorkageInfoRepository;
import com.atable.alcholknowledge.repository.CorkageStoreRepository;
import com.atable.alcholknowledge.repository.MemoryCkInfoRepository;
import com.atable.alcholknowledge.repository.MemoryCkStoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CorkageInfoServiceTest {

    CorkageInfoService ckInfoService;
    MemoryCkInfoRepository ckInfoRepository;
    MemoryCkStoreRepository ckStoreRepository;

    @BeforeEach
    public void beforeEach() {
        ckInfoRepository = new MemoryCkInfoRepository();
        ckStoreRepository = new MemoryCkStoreRepository();
        ckInfoService = new CorkageInfoService(ckInfoRepository, ckStoreRepository);
    }

    @AfterEach
    public void afterEach() {
        ckInfoRepository.clearStore();
        ckStoreRepository.clearStore();}

    @Test
    void register() {
        // given
        CorkageInfo ckInfo = new CorkageInfo();
        ckInfo.setAddr("address");

        // when
        Long id = ckInfoService.register(ckInfo);

        // then
        CorkageInfo findCkInfo = ckInfoService.findOne(id).get();
        assertThat(ckInfo.getId()).isEqualTo(findCkInfo.getId());
    }

    @Test
    public void 중복_매장_제외() {
        // given
        CorkageInfo ckInfo1 = new CorkageInfo();
        ckInfo1.setAddr("address 1");
        CorkageStore ckStore = new CorkageStore();
        ckStore.setAddr(ckInfo1.getAddr());

        CorkageInfo ckInfo2 = new CorkageInfo();
        ckInfo2.setAddr("address 1");

        // when
        ckInfoService.register(ckInfo1);
        CorkageStoreRepository ckStoreRepository = new MemoryCkStoreRepository();
        ckStoreRepository.save(ckStore);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> ckInfoService.register(ckInfo2));
        assertThat(e.getMessage()).isEqualTo("이미 접수 요청된 장소입니다.");
        // then
    }
    @Test
    void findCkInfos() {
        // given
        CorkageInfo ckInfo1 = new CorkageInfo();
        ckInfo1.setAddr("address 1");
        ckInfoService.register(ckInfo1);

        CorkageInfo ckInfo2 = new CorkageInfo();
        ckInfo2.setAddr("address 2");
        ckInfoService.register(ckInfo2);

        // when
        List<CorkageInfo> result = ckInfoService.findCkInfos();

        // then
        assertThat(result.size()).isEqualTo(2);
    }

}