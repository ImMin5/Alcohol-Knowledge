package com.atable.alcholknowledge;

import com.atable.alcholknowledge.repository.MemoryWineInfoReository;
import com.atable.alcholknowledge.repository.WineInfoRepository;
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
}
