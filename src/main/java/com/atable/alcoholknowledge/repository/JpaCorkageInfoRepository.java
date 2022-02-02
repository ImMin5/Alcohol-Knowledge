package com.atable.alcoholknowledge.repository;

import com.atable.alcoholknowledge.model.CorkageInfo;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaCorkageInfoRepository implements CorkageInfoRepository {

    private final EntityManager em;

    public JpaCorkageInfoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public CorkageInfo save(CorkageInfo cInfo) {
        em.persist(cInfo);
        return cInfo;
    }

    @Override
    public Optional<CorkageInfo> findById(Long id) {
        CorkageInfo ckInfo = em.find(CorkageInfo.class, id);
        return Optional.ofNullable(ckInfo);
    }

    @Override
    public List<CorkageInfo> findAll() {
        return em.createQuery("Select ckInfo from CorkageInfo as ckInfo", CorkageInfo.class)
                .getResultList();
    }
}