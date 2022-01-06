package com.atable.alcholknowledge.repository;

import com.atable.alcholknowledge.dto.WineInfoDto;
import com.atable.alcholknowledge.model.WineInfo;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        List<WineInfo> wineInfos  = em.createNativeQuery("SELECT  wi.pk, wi.nameEng ,wi.nameKor, wi.vintage , wi.price " +
                ", wi.dateCreated , wi.datePurchase ,wi.description , wi.store , wi.region ,wi.sizeBottle FROM WineInfo wi WHERE replace(wi.nameEng, ' ','') like :word or replace(wi.nameKor, ' ', '') LIKE :word",WineInfo.class)
                .setParameter("word","%"+word.replaceAll(" ","")+"%").getResultList();
        if(wineInfos.isEmpty() == true)
            return new ArrayList<WineInfo>();
        else
            return em.createNativeQuery("SELECT  wi.pk, wi.nameEng ,replace(wi.nameKor,' ','') , wi.vintage , wi.price , wi.dateCreated , wi.datePurchase ,wi.description , wi.store , wi.region ,wi.sizeBottle " +
                    "FROM WineInfo wi where replace(wi.nameEng, ' ','') LIKE :word or replace(wi.nameKor, ' ', '') LIKE :word",WineInfo.class)
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
        System.out.println("111");
        TypedQuery<WineInfo> query = em.createQuery("SELECT wi FROM WineInfo wi", WineInfo.class);
        System.out.println("2222");
        query.setFirstResult(index);
        query.setMaxResults(pageSize);
        System.out.println("3333");
        return query.getResultList();
    }

}
