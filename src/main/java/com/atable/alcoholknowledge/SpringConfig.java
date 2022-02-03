package com.atable.alcoholknowledge;

import com.atable.alcoholknowledge.aop.TimeTraceAop;
import com.atable.alcoholknowledge.repository.*;
import com.atable.alcoholknowledge.service.CorkageInfoService;
import com.atable.alcoholknowledge.service.CorkageStoreService;
import com.atable.alcoholknowledge.service.WineInfoService;
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

    /* Wine Related Beans */
    @Bean
    public WineInfoService wineInfoService(){ return new WineInfoService(wineInfoRepository()); }
    @Bean
    public WineInfoRepository wineInfoRepository(){
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
        return new JpaCorkageInfoRepository(em);
    }

    @Bean
    public CorkageStoreRepository corkageStoreRepository() {
        return new JpaCorkageStoreRepository(em);
    }

    @Bean
    public TimeTraceAop timeTreaceAop() {
        return new TimeTraceAop();
    }
}
