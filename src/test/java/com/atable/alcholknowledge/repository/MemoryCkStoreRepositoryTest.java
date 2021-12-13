package com.atable.alcholknowledge.repository;

import com.atable.alcholknowledge.model.CorkageStore;
import com.atable.alcholknowledge.repository.CorkageStoreRepository;
import com.atable.alcholknowledge.repository.MemoryCkStoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryCkStoreRepositoryTest {

    MemoryCkStoreRepository repository = new MemoryCkStoreRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        CorkageStore ckStore = new CorkageStore();
        ckStore.setName("name");
        repository.save(ckStore);

        CorkageStore result = repository.findById(ckStore.getId()).get();
        assertThat(ckStore).isEqualTo(result);
    }


    @Test
    public void findByAddr() {
        CorkageStore ckStore1 = new CorkageStore();
        ckStore1.setAddr("address 1");
        repository.save(ckStore1);

        CorkageStore ckStore2 = new CorkageStore();
        ckStore2.setAddr("address 2");
        repository.save(ckStore2);

        CorkageStore result = repository.findByAddr("address 1").get();
        assertThat(result).isEqualTo(ckStore1);
    }

    @Test
    void findAll() {
        CorkageStore ckStore1 = new CorkageStore();
        ckStore1.setAddr("address 1");
        repository.save(ckStore1);

        CorkageStore ckStore2 = new CorkageStore();
        ckStore2.setAddr("address 2");
        repository.save(ckStore2);

        List<CorkageStore> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}