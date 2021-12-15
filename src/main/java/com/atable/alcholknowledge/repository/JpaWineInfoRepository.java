package com.atable.alcholknowledge.repository;

import com.atable.alcholknowledge.model.WineInfo;

import javax.persistence.EntityManager;
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
    public Optional<WineInfo> findById(Long id) {
        WineInfo wineInfo = em.find(WineInfo.class, id);
        return Optional.ofNullable(wineInfo);

    }

    @Override
    public List<WineInfo> findByNameEng(String nameEng) {
        return em.createQuery("select wi from WineInfo wi where wi.name = :nameEng", WineInfo.class)
                .setParameter("naneEng",nameEng).getResultList();
    }

    @Override
    public List<WineInfo> findByNameKor(String nameKor) {
        return em.createQuery("select wi from WineInfo wi where wi.name = :nameKor", WineInfo.class)
                .setParameter("naneEng",nameKor).getResultList();
    }

    @Override
    public List<WineInfo> findAll() {
        return em.createQuery("select wi from WineInfo wi", WineInfo.class)
                .getResultList();
    }
}
