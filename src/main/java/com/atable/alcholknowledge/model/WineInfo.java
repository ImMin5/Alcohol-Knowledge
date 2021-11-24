package com.atable.alcholknowledge.model;

import java.sql.Date;
import java.sql.Timestamp;

public class WineInfo {
    private long pk;
    private String nameEng;
    private String nameKor;
    private int vintage;
    private int price;
    private Timestamp dateCreated;
    private Date datePurchase;
    private String desc;
    private String store;
    private String region;
    private String size;

    public int getPrice(){return this.price;}
    public void setPrice(int price){ this.price = price;}
    public WineInfo getInfo(){
        return this;
    }
    public void setInfo(long pk, String nameEng, String nameKor, int vintage, int price, Timestamp dateCreated, Date datePurchase,
                        String desc, String store, String region, String size){
        this.pk = pk;
        this.nameEng = nameEng;
        this.nameKor = nameKor;
        this.vintage = vintage;
        this.price = price;
        this.dateCreated = dateCreated;
        this.datePurchase = datePurchase;
        this.desc = desc;
        this.store = store;
        this.region = region;
        this.size = size;
    }
}
