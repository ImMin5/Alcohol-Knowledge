package com.atable.alcholknowledge.service;

import com.atable.alcholknowledge.model.WineInfo;
import com.atable.alcholknowledge.repository.WineInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //db에 넣어던 데이터 롤백해준다.
class WineInfoServiceTest {


    @Autowired WineInfoService wineInfoService;
    @Autowired WineInfoRepository wineInfoRepository;

    @Test
    void submit() {
        //given
        WineInfo wineInfo = new WineInfo();
        wineInfo.setNameKor("파이퍼하이직");
        //when
        Long savedId = wineInfoService.submit(wineInfo);

        //then
        List<WineInfo> result = wineInfoRepository.findAll();
        org.assertj.core.api.Assertions.assertThat(wineInfoService.findWineInfos()).isEqualTo(result);

    }

    @Test
    void findWineInfos() {
    }

    @Test
    void findWineInfosKor() {
    }

    @Test
    void findWineInfosEng() {
    }
}