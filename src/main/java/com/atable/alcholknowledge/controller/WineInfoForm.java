package com.atable.alcholknowledge.controller;

import java.sql.Date;
import java.sql.Timestamp;

public class WineInfoForm {
    private String nameEng;
    private String nameKor;
    private int vintage;
    private int price;
    private Timestamp dateCreated;
    private Date datePurchase;
    private String description;
    private String store;
    private String region;
    private String sizeBottle;

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNameKor() {
        return nameKor;
    }

    public void setNameKor(String nameKor) {
        this.nameKor = nameKor;
    }

    public int getVintage() {
        return vintage;
    }

    public void setVintage(int vintage) {
        this.vintage = vintage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Date datePurchase) {
        this.datePurchase = datePurchase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSizeBottle() {
        return this.sizeBottle;
    }

    public void setSizeBottle(String sizeBottle) {
        this.sizeBottle = sizeBottle;
    }
}
