package com.atable.alcholknowledge;

import com.atable.alcholknowledge.repository.*;
import com.atable.alcholknowledge.service.CorkageInfoService;
import com.atable.alcholknowledge.service.CorkageStoreService;
import com.atable.alcholknowledge.service.WineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em ;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public WineInfoService wineInfoService(){
        return new WineInfoService(wineInfoRepository());
    }
    @Bean
    public WineInfoRepository wineInfoRepository(){

        //return new MemoryWineInfoReository();
        return new JpaWineInfoRepository(em);
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
