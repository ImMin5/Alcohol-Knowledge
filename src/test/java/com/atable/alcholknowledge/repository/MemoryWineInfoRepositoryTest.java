package com.atable.alcholknowledge.repository;

import com.atable.alcholknowledge.model.WineInfo;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryWineInfoRepositoryTest {
    MemoryWineInfoReository repository = new MemoryWineInfoReository();

    @Test
    public void sava(){
        WineInfo wineInfo = new WineInfo();
        wineInfo.setNameKor("파이퍼하이직");
        wineInfo.setPrice(10000);
        repository.save(wineInfo);

        wineInfo = new WineInfo();
        wineInfo.setNameKor("파이퍼하이직");
        wineInfo.setPrice(20000);
        repository.save(wineInfo);

        ArrayList<WineInfo> list = new ArrayList<>();
        List<WineInfo> result = repository.findByNameKor(wineInfo.getNameKor());
        for(WineInfo wi : result){
            System.out.print("name :"+ wi.getNameKor());
            System.out.println(" price :"+ wi.getPrice());
        }
    }
}
