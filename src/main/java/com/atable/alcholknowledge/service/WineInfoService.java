package com.atable.alcholknowledge.service;

import com.atable.alcholknowledge.dto.WineInfoDto;
import com.atable.alcholknowledge.model.WineInfo;
import com.atable.alcholknowledge.repository.WineInfoRepository;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
public class WineInfoService {
    private WineInfoRepository wineInfoRepository;

    public WineInfoService(WineInfoRepository wineInfoRepository){
        this.wineInfoRepository = wineInfoRepository;
    }

    /*
    * 와인 정보 입력
    * */

    public Long submit(WineInfo wineInfo){
        System.out.println("submit");
        wineInfoRepository.save(wineInfo);
        return wineInfo.getPk();
    }

    /*
     * 전체 리스트 조회
     */
    public List<WineInfoDto> findWineInfos(){
        List<WineInfoDto> wineInfos = new ArrayList<>();
        for(WineInfo wineInfo : wineInfoRepository.findAll()){
            wineInfos.add(new WineInfoDto(wineInfo));
        }
        return wineInfos;
    }


    /*
    * 매개변수로 들어오는 단어가 포함되어있으면 전부 검색
    * */
    public List<WineInfoDto> findWineInfosByWord(String word){
        List<WineInfoDto> wineInfos = new ArrayList<>();
        for(WineInfo wineInfo : wineInfoRepository.findByWord(word)){
            wineInfos.add(new WineInfoDto(wineInfo));
        }
        return wineInfos;
    }

    public String deleteWineInfoById(Long pk){
        if(wineInfoRepository.delete(pk) > 0){
            return "good";
        }
        else
            return "fail";

    }

}
