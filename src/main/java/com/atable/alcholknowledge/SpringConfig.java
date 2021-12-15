package com.atable.alcholknowledge;

import com.atable.alcholknowledge.repository.JpaWineInfoRepository;
import com.atable.alcholknowledge.repository.MemoryWineInfoReository;
import com.atable.alcholknowledge.repository.WineInfoRepository;
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
}
