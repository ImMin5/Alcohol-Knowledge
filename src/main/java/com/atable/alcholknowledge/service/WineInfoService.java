package com.atable.alcholknowledge.service;

import com.atable.alcholknowledge.repository.MemoryWineInfoReository;
import com.atable.alcholknowledge.repository.WineInfoRepository;

public class WineInfoService {
    private final WineInfoRepository wineInfoRepository = new MemoryWineInfoReository();

}
