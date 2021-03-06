package com.atable.alcoholknowledge.repository;

import com.atable.alcoholknowledge.model.CorkageStore;

import java.util.*;

public class MemoryCkStoreRepository implements CorkageStoreRepository{

    private static Map<Long, CorkageStore> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public CorkageStore save(CorkageStore cStore) {
        cStore.setId(++sequence);
        store.put(sequence, cStore);
        return cStore;
    }

    @Override
    public Optional<CorkageStore> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<CorkageStore> findByName(String name) {

        /*
        TBD
        * Return list of all the CorkageStores found by name.
        * Test this method
         */
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public Optional<CorkageStore> findByAddr(String addr) {
        return store.values().stream()
                .filter(member -> member.getAddr().equals(addr))
                .findAny();
    }

    @Override
    public List<CorkageStore> findByKeyword(String keyword) {
        return null;
    }

    @Override
    public List findPageByKeyword(String keyword, int index, int pageSize) {
        return null;
    }

    @Override
    public List<CorkageStore> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<CorkageStore> findPage(int index, int pageSize) {
        return null;
    }

    public void clearStore() {
        store.clear();
    }
}
