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
    public void checkAsCorkageStore(Long id) {
        CorkageInfo corkageInfo = em.find(CorkageInfo.class, id);
        corkageInfo.setChecked(corkageInfo.isChecked() + 1);
    }

    @Override
    public Optional<CorkageInfo> findById(Long id) {
        CorkageInfo ckInfo = em.find(CorkageInfo.class, id);
        return Optional.ofNullable(ckInfo);
    }

    @Override
    public Long findByAddr(String addr) {
        List<CorkageInfo> result = em.createQuery("SELECT ckInfo FROM CorkageInfo AS ckInfo WHERE ckInfo.addr = :addr", CorkageInfo.class)
                .setParameter("addr", addr)
                .getResultList();
        Long id = ((result.isEmpty() ? 0L : result.stream().findFirst().get().getId()));
        return id;
    }

    @Override
    public List<CorkageInfo> findAll() {
        return em.createQuery("Select ckInfo from CorkageInfo as ckInfo", CorkageInfo.class)
                .getResultList();
    }
}