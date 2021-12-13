package com.atable.alcholknowledge.repository;
import com.atable.alcholknowledge.model.WineInfo;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class MemoryWineInfoReository implements WineInfoRepository{
    private static Map<Long, WineInfo> wineinfos = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    @Override
    public WineInfo save(WineInfo wineInfo) {
        wineInfo.setPk(++sequence);
        //wineinfo.setInfo();
        wineinfos.put(wineInfo.getPk(), wineInfo);
        return wineInfo;
    }

    @Override
    public Optional<WineInfo> findById(Long id) {
        return Optional.ofNullable(wineinfos.get(id));
    }

    @Override
    public List<WineInfo> findByNameEng(String nameEng) {
        ArrayList<WineInfo> list = new ArrayList<>();
        for( Map.Entry<Long, WineInfo> entryset : wineinfos.entrySet()){
            if(entryset.getValue().getNameKor().equals(nameEng)){
                list.add(entryset.getValue());
            }
        }
        return list;
    }

    @Override
    public List<WineInfo> findByNameKor(String nameKor) {
        ArrayList<WineInfo> list = new ArrayList<>();
        for( Map.Entry<Long, WineInfo> entryset : wineinfos.entrySet()){
            if(entryset.getValue().getNameKor().equals(nameKor)){
                list.add(entryset.getValue());
            }
        }
        return list;
    }

    @Override
    public List<WineInfo> findAll() {
        System.out.println("find all : "+ wineinfos.size());
        return new ArrayList<>(wineinfos.values());
    }

    public void clearWineInfo(){
        wineinfos.clear();
    }
}
