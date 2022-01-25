package com.atable.alcholknowledge.service;

import com.atable.alcholknowledge.dto.WineInfoDto;
import com.atable.alcholknowledge.model.WineInfo;
import com.atable.alcholknowledge.repository.WineInfoRepository;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


//예외 발생시 자동을 roll-back
//transaction begin(시작), 과 commit(끝)을 자동으로 실행해준다.
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
    public List<WineInfoDto> findWineInfoDtos(){
        List<WineInfoDto> wineInfos = new ArrayList<>();
        for(WineInfo wineInfo : wineInfoRepository.findAll()){
            wineInfos.add(new WineInfoDto(wineInfo));
        }
        return wineInfos;
    }

    public List<WineInfo> findWineInfos(){
        List<WineInfo> wineInfos = wineInfoRepository.findAll();
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

    /*
    * 페이지 네이션
    */
    public List<WineInfoDto> findWineInfosPage(int index, int pageSize){
        List<WineInfoDto> wineInfos = new ArrayList<>();
        for(WineInfo wineInfo : wineInfoRepository.findPagination(index, pageSize)){
            if(wineInfo == null) break;
            wineInfos.add(new WineInfoDto(wineInfo));
        }
        return wineInfos;
    }

    /*
    * 검색 페이지 네이션션
   */
    public List<WineInfoDto> findByWordWineInfosPage(int index, int pageSize, String word){
        List<WineInfoDto> wineInfos = new ArrayList<>();
        for(WineInfo wineInfo : wineInfoRepository.findByWordPagination(index, pageSize, word)){
            if(wineInfo == null) break;
            wineInfos.add(new WineInfoDto(wineInfo));
        }
        return wineInfos;
    }

    /*
    * pk로 와인정보 삭제
    * */
    public String deleteWineInfoById(Long pk){
        if(wineInfoRepository.delete(pk) > 0){
            return "success";
        }
        else
            return "failed";

    }

}
