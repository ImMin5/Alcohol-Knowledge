package com.atable.alcholknowledge.repository;
import com.atable.alcholknowledge.model.WineInfo;

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
    public WineInfo save(WineInfo wineinfo) {
        wineinfo.setPk(++sequence);
        wineinfos.put(wineinfo.getPk(), wineinfo);
        return null;
    }

    @Override
    public Optional<WineInfo> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<WineInfo> findByNameEng(String nameEng) {
        return Optional.empty();
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
        return null;
    }
}
