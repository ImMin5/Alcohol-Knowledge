package com.atable.alcoholknowledge.model;

import com.atable.alcoholknowledge.dto.WineInfoForm;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="wineinfo")
public class WineInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;
    @Column(name="nameeng")
    private String nameEng;
    @Column(name="namekor")
    private String nameKor;
    @Column(name="vintage")
    private Integer vintage;
    @Column(name="price")
    private Integer price;
    @Column(name="datecreated")  @CreationTimestamp @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp dateCreated;
    @Column(name="datepurchase") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date datePurchase;
    @Column(name="description")
    private String description;
    @Column(name="store")
    private String store;
    @Column(name="region")
    private String region;
    @Column(name="sizebottle")
    private String sizeBottle;

    public WineInfo(){};

    public WineInfo(WineInfoForm wineInfoForm){
        this.nameEng = wineInfoForm.getNameEng();
        this.nameKor = wineInfoForm.getNameKor();
        this.vintage = wineInfoForm.getVintage();
        this.price = wineInfoForm.getPrice();
        this.dateCreated = wineInfoForm.getDateCreated();
        this.datePurchase = wineInfoForm.getDatePurchase();
        this.description = wineInfoForm.getDescription();
        this.store = wineInfoForm.getStore();
        this.region = wineInfoForm.getRegion();
        this.sizeBottle = wineInfoForm.getSizeBottle();
    }


    //String nameEng, String nameKor, int vintage, int price, Timestamp dateCreated, Date datePurchase,
    //                        String desc, String store, String region, String size
}
