package com.atable.alcholknowledge.model;

import com.atable.alcholknowledge.controller.WineInfoForm;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Locale;

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

    public void setNameEng(String nameEng) {this.nameEng = nameEng; }

    public int getVintage() {return vintage; }
    public void setVintage(int vintage) {this.vintage = vintage; }

    public Timestamp getDateCreated() {return dateCreated; }
    public void setDateCreated(Timestamp dateCreated) {this.dateCreated = dateCreated; }

    public Date getDatePurchase() {return datePurchase; }
    public void setDatePurchase(Date datePurchase) {this.datePurchase = datePurchase; }

    public String getDesc() {return desc; }
    public void setDesc(String desc) {this.desc = desc; }

    public String getStore() {return store; }

    public void setStore(String store) {this.store = store; }

    public String getRegion() {return region; }

    public void setRegion(String region) {this.region = region; }

    public String getSize() {return size;}

    public void setSize(String size) {this.size = size; }

    public String getNameKor(){return this.nameKor;}
    public void setNameKor(String nameKor){ this.nameKor = nameKor;}
    public String getNameEng(){
        this.nameEng.toLowerCase().contains(nameEng.toLowerCase());
        return this.nameEng;
    }
    public long getPk(){ return this.pk;}
    public void setPk(long pk){ this.pk = pk;}
    public int getPrice(){return this.price;}
    public void setPrice(int price){ this.price = price;}
    public WineInfo getInfo(){
        return this;
    }
    public void setInfo(WineInfoForm form){
        this.nameEng = form.getNameEng();
        this.nameKor = form.getNameKor();
        this.vintage = form.getVintage();
        this.price = form.getPrice();
        this.dateCreated = form.getDateCreated();
        this.datePurchase = form.getDatePurchase();
        this.desc = form.getDesc();
        this.store = form.getStore();
        this.region = form.getRegion();
        this.size = form.getSize();
    }
    //String nameEng, String nameKor, int vintage, int price, Timestamp dateCreated, Date datePurchase,
    //                        String desc, String store, String region, String size
}
