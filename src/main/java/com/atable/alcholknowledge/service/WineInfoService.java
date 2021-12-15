package com.atable.alcholknowledge.service;

import com.atable.alcholknowledge.model.WineInfo;
import com.atable.alcholknowledge.repository.MemoryWineInfoReository;
import com.atable.alcholknowledge.repository.WineInfoRepository;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

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
        wineInfoRepository.save(wineInfo);
        return wineInfo.getPk();
    }

    /*
     * 전체 리스트 조회
     */
    public List<WineInfo> findWineInfos(){
        return wineInfoRepository.findAll();
    }

    /*
    * 한글 이름으로 조회
    */
    public List<WineInfo> findWineInfosKor(String nameKor){
        return wineInfoRepository.findByNameKor(nameKor);
    }

    /*
    * 영문 이름으로 조회
    */
    public List<WineInfo> findWineInfosEng(String nameEng){
        return wineInfoRepository.findByNameKor(nameEng);
    }
}
