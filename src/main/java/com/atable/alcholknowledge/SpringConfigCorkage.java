package com.atable.alcholknowledge;

import com.atable.alcholknowledge.repository.CorkageInfoRepository;
import com.atable.alcholknowledge.repository.CorkageStoreRepository;
import com.atable.alcholknowledge.repository.MemoryCkInfoRepository;
import com.atable.alcholknowledge.repository.MemoryCkStoreRepository;
import com.atable.alcholknowledge.service.CorkageInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfigCorkage {

    @Bean
    public CorkageInfoService corkageInfoService() {
        return new CorkageInfoService(corkageInfoRepository(), corkageStoreRepository());
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
