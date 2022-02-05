package com.atable.alcoholknowledge.service;

import com.atable.alcoholknowledge.model.CorkageInfo;
import com.atable.alcoholknowledge.model.CorkageStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class CkIntegrationTest {

    @Autowired
    CorkageInfoService ckInfoService;
    @Autowired
    CorkageStoreService ckStoreService;

    @Test
    void 콜키지_접수() {
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
    public void 콜키지_중복_매장_제외() {
        // given
        CorkageInfo ckInfo1 = new CorkageInfo();
        ckInfo1.setAddr("address A");
        CorkageStore ckStore = new CorkageStore();
        ckStore.setAddr(ckInfo1.getAddr());
        ckStore.setName("name A");

        CorkageInfo ckInfo2 = new CorkageInfo();
        ckInfo2.setAddr("address A");

        // when
        ckInfoService.register(ckInfo1);
        ckStoreService.register(ckStore);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> ckInfoService.register(ckInfo2));
        assertThat(e.getMessage()).isEqualTo("이미 접수 요청된 장소입니다.");
        // then
    }
    @Test
    void 접수된_매장_목록_조회_관리자() {
        // given
        int ckInfoSize = ckInfoService.findCkInfos().size();
        CorkageInfo ckInfo1 = new CorkageInfo();
        ckInfo1.setAddr("address 1");
        ckInfoService.register(ckInfo1);

        CorkageInfo ckInfo2 = new CorkageInfo();
        ckInfo2.setAddr("address 2");
        ckInfoService.register(ckInfo2);

        // when
        List<CorkageInfo> result = ckInfoService.findCkInfos();

        // then
        assertThat(result.size()).isEqualTo(ckInfoSize + 2);
    }

    @Test
    void 콜키지_매장_등록_관리자() {
        // given
        CorkageStore ckStore = new CorkageStore();
        ckStore.setAddr("address");
        ckStore.setName("name");

        // when
        Long id = ckStoreService.register(ckStore);

        // then
        CorkageStore findCkInfo = ckStoreService.findOne(id).get();
        assertThat(ckStore.getId()).isEqualTo(findCkInfo.getId());
    }

    @Test
    void 콜키지_매장_찾기_byAddr() {
        // given
        CorkageStore ckStore1 = new CorkageStore();
        ckStore1.setAddr("address 1");
        ckStore1.setName("name1");
        ckStoreService.register(ckStore1);

        CorkageStore ckStore2 = new CorkageStore();
        ckStore2.setAddr("address 2");
        ckStore2.setName("name2");
        ckStoreService.register(ckStore2);

        CorkageStore result = ckStoreService.findOneByAddr("address 1").get();
        assertThat(result).isEqualTo(ckStore1);
    }

    @Test
    void 콜키지_매장_목록_조회() {
        int ckStoreSize = ckStoreService.findCkStores().size();
        CorkageStore ckStore1 = new CorkageStore();
        ckStore1.setAddr("address 1");
        ckStore1.setName("name1");
        ckStoreService.register(ckStore1);

        CorkageStore ckStore2 = new CorkageStore();
        ckStore2.setAddr("address 2");
        ckStore2.setName("name2");
        ckStoreService.register(ckStore2);

        List<CorkageStore> result = ckStoreService.findCkStores();
        assertThat(result.size()).isEqualTo(ckStoreSize + 2);
    }
}
