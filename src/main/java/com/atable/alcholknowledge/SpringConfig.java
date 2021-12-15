package com.atable.alcholknowledge;

import com.atable.alcholknowledge.repository.*;
import com.atable.alcholknowledge.service.CorkageInfoService;
import com.atable.alcholknowledge.service.CorkageStoreService;
import com.atable.alcholknowledge.service.WineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

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
        return new JpaCkInfoRepository(em);
    }

    @Bean
    public CorkageStoreRepository corkageStoreRepository() {
        return new JpaCkStoreRepository(em);
    }
}
