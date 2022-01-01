package com.atable.alcholknowledge.repository;

import com.atable.alcholknowledge.dto.WineInfoDto;
import com.atable.alcholknowledge.model.WineInfo;
import org.hibernate.query.Query;

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
        List<WineInfo> wineInfos  = em.createNativeQuery("select  wi.pk, wi.nameEng ,wi.nameKor, wi.vintage , wi.price , wi.dateCreated , wi.datePurchase ,wi.description , wi.store , wi.region ,wi.sizeBottle from WineInfo wi where replace(wi.nameEng, ' ','') like :word or replace(wi.nameKor, ' ', '') like :word",WineInfo.class)
                .setParameter("word","%"+word.replaceAll(" ","")+"%").getResultList();
        if(wineInfos.isEmpty() == true)
            return new ArrayList<WineInfo>();
        else
            return em.createNativeQuery("select  wi.pk, wi.nameEng ,replace(wi.nameKor,' ','') , wi.vintage , wi.price , wi.dateCreated , wi.datePurchase ,wi.description , wi.store , wi.region ,wi.sizeBottle from WineInfo wi where replace(wi.nameEng, ' ','') like :word or replace(wi.nameKor, ' ', '') like :word",WineInfo.class)
                .setParameter("word","%"+word.replaceAll(" ","")+"%").getResultList();
    }
    //select wi from WineInfo wi where wi.name = :nameEng
    //wi.pk, wi.nameEng ,wi.nameKor , wi.vintage , wi.price , wi.dateCreated , wi.datePurchase ,wi.description , wi.store , wi.region ,wi.sizeBottle
    @Override
    public List<WineInfo> findAll() {
        return em.createQuery("select wi from WineInfo wi", WineInfo.class)
                .getResultList();
    }

}
