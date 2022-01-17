package com.atable.alcholknowledge.repository;

import com.atable.alcholknowledge.dto.WineInfoDto;
import com.atable.alcholknowledge.model.WineInfo;
import com.atable.alcholknowledge.service.WineInfoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class MemoryWineInfoRepositoryTest {

    @Autowired WineInfoRepository repository;


    @Test
    public void save(){
        WineInfo wineInfo = new WineInfo();
        wineInfo.setNameKor("파이퍼하이직");
        wineInfo.setPrice(10000);
        repository.save(wineInfo);

        wineInfo = new WineInfo();
        wineInfo.setNameKor("파이퍼하이직");
        wineInfo.setPrice(20000);
        repository.save(wineInfo);

        wineInfo = new WineInfo();
        wineInfo.setNameKor("네비게이션");
        wineInfo.setPrice(12000);
        repository.save(wineInfo);

        ArrayList<WineInfo> list = new ArrayList<>();
        List<WineInfo> result = repository.findByWord("파이퍼하이직");
        //Assertions.assertEquals(repository.findAll(),result);
        Assertions.assertThat(repository.findByWord("파이퍼하이직")).isEqualTo(result.get(0));

    }

    @Test
    public void findByAll(){
        WineInfo wineInfo = new WineInfo();
        wineInfo.setNameKor("파이퍼하이직");
        wineInfo.setPrice(10000);
        repository.save(wineInfo);

        wineInfo = new WineInfo();
        wineInfo.setNameKor("파이퍼하이직");
        wineInfo.setPrice(20000);
        repository.save(wineInfo);

        wineInfo = new WineInfo();
        wineInfo.setNameKor("네비게이션");
        wineInfo.setPrice(12000);
        repository.save(wineInfo);

        List<WineInfo> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(3);

    }

    @Test
    public void findByName(String name){

    }
}
