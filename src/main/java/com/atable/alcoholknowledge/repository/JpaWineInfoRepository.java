package com.atable.alcoholknowledge.repository;

import com.atable.alcoholknowledge.model.WineInfo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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
        return em.createNativeQuery("SELECT  wi.pk, wi.nameEng ,wi.nameKor, wi.vintage , wi.price " +
                ", wi.dateCreated , wi.datePurchase ,wi.description , wi.store , wi.region ,wi.sizeBottle FROM WineInfo wi WHERE replace(wi.nameEng, ' ','') like :word or replace(wi.nameKor, ' ', '') LIKE :word",WineInfo.class)
                .setParameter("word","%"+word.replaceAll(" ","")+"%").getResultList();

    }
    //select wi from WineInfo wi where wi.name = :nameEng
    //wi.pk, wi.nameEng ,wi.nameKor , wi.vintage , wi.price , wi.dateCreated , wi.datePurchase ,wi.description , wi.store , wi.region ,wi.sizeBottle
    @Override
    public List<WineInfo> findAll() {
        return em.createQuery("SELECT wi FROM WineInfo wi", WineInfo.class)
                .getResultList();
    }

    @Override
    public Integer delete(Long pk) {
        return em.createQuery("DELETE from WineInfo wi WHERE wi.pk =:pk").setParameter("pk",pk).executeUpdate();
    }

    @Override
    public List<WineInfo> findPagination(int index, int pageSize) {
        TypedQuery<WineInfo> query = em.createQuery("SELECT wi FROM WineInfo wi ORDER BY wi.datePurchase", WineInfo.class);
        query.setFirstResult(index);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<WineInfo> findByWordPagination(int index, int pageSize, String word) {
        TypedQuery<WineInfo> query = em.createQuery("SELECT  wi FROM WineInfo wi WHERE replace(wi.nameEng, ' ','') like :word or replace(wi.nameKor, ' ', '') LIKE :word",WineInfo.class)
                .setParameter("word","%"+word.replaceAll(" ","")+"%");
        query.setFirstResult(index);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

}
