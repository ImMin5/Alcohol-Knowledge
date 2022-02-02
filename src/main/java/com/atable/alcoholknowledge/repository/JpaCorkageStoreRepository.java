package com.atable.alcoholknowledge.repository;

import com.atable.alcoholknowledge.model.CorkageStore;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JpaCorkageStoreRepository implements CorkageStoreRepository{

    private final EntityManager em;

    public JpaCorkageStoreRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public CorkageStore save(CorkageStore cStore) {
        em.persist(cStore);
        return cStore;
    }

    @Override
    public Optional<CorkageStore> findById(Long id) {
        CorkageStore ckStore = em.find(CorkageStore.class, id);
        return Optional.ofNullable(ckStore);
    }

    @Override
    public Optional<CorkageStore> findByName(String name) {
        List<CorkageStore> result = em.createQuery("Select ckStore from CorkageStore ckStore where ckStore.name = :name", CorkageStore.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<CorkageStore> findByAddr(String addr) {
        List<CorkageStore> result = em.createQuery("Select ckStore from CorkageStore ckStore where ckStore.addr = :addr", CorkageStore.class)
                .setParameter("addr", addr)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<CorkageStore> findByKeyword(String keyword) {
        System.out.println("**SEARCH: "+keyword);
        String sql = "SELECT * FROM corkage_store ck" +
                " WHERE replace(ck.addr, ' ', '') LIKE :keyword" +
                " OR replace(ck.name, ' ', '') LIKE :keyword";
        List result = em.createNativeQuery(sql, CorkageStore.class)
                .setParameter("keyword", keyword)
                .getResultList();
        return new ArrayList<CorkageStore>(result);
    }

    @Override
    public List<CorkageStore> findPageByKeyword(String keyword, int index, int pageSize) {
        String sql = "SELECT * FROM corkage_store ck" +
                " WHERE replace(ck.addr, ' ', '') LIKE :keyword" +
                " OR replace(ck.name, ' ', '') LIKE :keyword";

        List result =  em.createNativeQuery(sql, CorkageStore.class)
                .setParameter("keyword", keyword)
                .setFirstResult(index)
                .setMaxResults(pageSize)
                .getResultList();

        return new ArrayList<CorkageStore>(result);
    }

        @Override
    public List<CorkageStore> findAll() {
        return em.createQuery("Select ckStore from CorkageStore ckStore", CorkageStore.class)
                .getResultList();
    }

    @Override
    public List<CorkageStore> findPage(int index, int pageSize) {
        List<CorkageStore> result = em.createQuery("select ckStore from CorkageStore ckStore", CorkageStore.class)
                .setFirstResult(index)
                .setMaxResults(pageSize)
                .getResultList();

        return result;
    }
}
