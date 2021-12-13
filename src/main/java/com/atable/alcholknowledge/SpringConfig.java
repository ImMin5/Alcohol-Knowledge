package com.atable.alcholknowledge;

import com.atable.alcholknowledge.repository.*;
import com.atable.alcholknowledge.service.CorkageInfoService;
import com.atable.alcholknowledge.service.CorkageStoreService;
import com.atable.alcholknowledge.service.WineInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public WineInfoService wineInfoService(){
        return new WineInfoService(wineInfoRepository());
    }
    @Bean
    public WineInfoRepository wineInfoRepository(){
        return new MemoryWineInfoReository();
    }

    /* Corkage Related Beans */
    @Bean
    public CorkageInfoService corkageInfoService() {
        return new CorkageInfoService(corkageInfoRepository(), corkageStoreRepository());
    }

    @Bean
    public CorkageStoreService corkageStoreService() {
        return new CorkageStoreService(corkageStoreRepository());
    }
    @Bean
    public CorkageInfoRepository corkageInfoRepository() {
        return new MemoryCkInfoRepository();
    }

    @Bean
    public CorkageStoreRepository corkageStoreRepository() {
        return new MemoryCkStoreRepository();
    }
}
