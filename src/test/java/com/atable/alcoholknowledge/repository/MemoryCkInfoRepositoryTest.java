package com.atable.alcoholknowledge.repository;

import com.atable.alcoholknowledge.model.CorkageInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryCkInfoRepositoryTest {

    MemoryCkInfoRepository repository = new MemoryCkInfoRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save() {
        CorkageInfo ckInfo = new CorkageInfo();
        ckInfo.setAddr("address");
        repository.save(ckInfo);

        CorkageInfo result = repository.findById(ckInfo.getId()).get();
        assertThat(ckInfo).isEqualTo(result);
    }

    @Test
    public void findAll() {
        CorkageInfo ckInfo1 = new CorkageInfo();
        ckInfo1.setAddr("address 1");
        repository.save(ckInfo1);

        CorkageInfo ckInfo2 = new CorkageInfo();
        ckInfo2.setAddr("address 2");
        repository.save(ckInfo2);

        List<CorkageInfo> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
