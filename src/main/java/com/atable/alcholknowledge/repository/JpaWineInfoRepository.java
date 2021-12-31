package com.atable.alcholknowledge.repository;

import com.atable.alcholknowledge.dto.WineInfoDto;
import com.atable.alcholknowledge.model.WineInfo;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JpaWineInfoRepository implements WineInfoRepository {
    private final EntityManager em;

    public JpaWineInfoRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public WineInfo save(WineInfo wineInfo) {
        em.persist(wineInfo);
        return wineInfo;
    }

    @Override
    public WineInfo findById(Long id) {
        WineInfo wineInfo = em.find(WineInfo.class, id);
        //return Optional.ofNullable(wineInfo);
        return wineInfo;

    }

    @Override
    public List<WineInfo> findByWord(String word) {
        return em.createQuery("select wi from WineInfo wi where wi.nameEng like :word or wi.nameKor like :word", WineInfo.class)
                .setParameter("word",word).getResultList();
    }
    //select wi from WineInfo wi where wi.name = :nameEng

    @Override
    public List<WineInfo> findAll() {
        return em.createQuery("select wi from WineInfo wi", WineInfo.class)
                .getResultList();
    }

}
