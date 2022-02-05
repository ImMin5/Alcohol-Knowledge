package com.atable.alcoholknowledge.service;

import com.atable.alcoholknowledge.dto.WineInfoDto;
import com.atable.alcoholknowledge.model.WineInfo;
import com.atable.alcoholknowledge.repository.WineInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
@Transactional //db에 넣어던 데이터 롤백해준다.  -> beforeeach, aftereach를 안해도됨
class WineInfoServiceTest {

    @Autowired WineInfoService wineInfoService;
    @Autowired WineInfoRepository wineInfoRepository;

    @Test
    void 와인정보_생성() {
        //given
        WineInfo wineInfo = new WineInfo();
        wineInfo.setNameKor("파이퍼하이직");
        wineInfo.setNameEng("piper");
        wineInfo.setPrice(10000);
        wineInfo.setVintage(2018);
        wineInfo.setRegion("서울");
        wineInfo.setStore("이마트");
        wineInfo.setSizeBottle("750");
        wineInfo.setDatePurchase(new Date(20210101));
        wineInfo.setDateCreated(new Timestamp(System.currentTimeMillis()));
        //when
        Long savedId = wineInfoService.submit(wineInfo);
        wineInfo.setPk(savedId);

        //then
        WineInfo result = wineInfoRepository.findById(savedId);
        org.assertj.core.api.Assertions.assertThat(wineInfo).isEqualTo(result);

    }

    @Test
    void 와인정보_조회() {

        //given
        WineInfo wineInfo = new WineInfo();
        wineInfo.setNameKor("파이퍼하이직");
        wineInfo.setNameEng("piper");
        wineInfo.setDateCreated(new Timestamp(System.currentTimeMillis()));
        wineInfo.setPrice(10000);

        WineInfo wineInfo1 = new WineInfo();
        wineInfo.setNameKor("파이퍼하이직1");
        wineInfo.setNameEng("piper1");
        wineInfo.setPrice(10000);

        WineInfo wineInfo2 = new WineInfo();
        wineInfo.setNameKor("s");
        wineInfo.setNameEng("navi2");
        wineInfo.setPrice(10000);
        //when
        wineInfoService.submit(wineInfo);
        wineInfoService.submit(wineInfo1);
        wineInfoService.submit(wineInfo2);


        //then
        List<WineInfoDto> wineInfos = wineInfoService.findWineInfosByWord("파이퍼");
        org.assertj.core.api.Assertions.assertThat(wineInfoService.findWineInfosByWord("파이퍼")).isEqualTo(wineInfos);
    }

    @Test
    public void 와인정보_검색(){
        //given
        String word = ("나다");
        WineInfo wineInfo = new WineInfo();
        wineInfo.setNameKor("가나 다라");
        wineInfo.setNameEng("gana dara");
        wineInfo.setPrice(10000);
        wineInfo.setVintage(2018);
        wineInfo.setRegion("서울");
        wineInfo.setStore("이마트");
        wineInfo.setSizeBottle("750");
        wineInfo.setDatePurchase(new Date(20210101));
        wineInfo.setDateCreated(new Timestamp(System.currentTimeMillis()));

        //when
        wineInfoService.submit(wineInfo);

        //then
        List<WineInfoDto> result = wineInfoService.findWineInfosByWord(word);
        org.assertj.core.api.Assertions.assertThat(wineInfoService.findWineInfosByWord(word)).isEqualTo(result);

    }

    @Test
    public void 와인정보_삭제(){
        //given
        WineInfo wineInfo = new WineInfo();
        wineInfo.setNameKor("가나 다라");
        wineInfo.setNameEng("gana dara");
        wineInfo.setPrice(10000);
        wineInfo.setVintage(2018);
        wineInfo.setRegion("서울");
        wineInfo.setStore("이마트");
        wineInfo.setSizeBottle("750");
        wineInfo.setDatePurchase(new Date(20210101));
        wineInfo.setDateCreated(new Timestamp(System.currentTimeMillis()));

        //when
        Long pk = wineInfoService.submit(wineInfo);
        System.out.println("pk : " + pk);


        //then
        String result = wineInfoService.deleteWineInfoById(pk);
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("good");
    }
    @Test
    public void 와인정보_페이지네이션(){
        //given
        int pageSize = 1;
        //when
            for(int i=0; i<3; i++) {
                List<WineInfo> result = wineInfoRepository.findPagination(i,1);
                for(WineInfo w: result){
                    System.out.println(w.getNameEng());
                }
            }
        //then

    }

    @Test
    public void 와인검색_페이지네이션(){
        //given
        int pageSize = 1;
        //when
        for(int i=0; i<3; i++) {
            List<WineInfoDto> result = wineInfoService.findByWordWineInfosPage(i,2,"김민호");
            for(WineInfoDto w: result){
                System.out.println(w.getNameEng());
            }
        }
        //then

    }

}