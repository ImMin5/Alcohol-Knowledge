package com.atable.alcoholknowledge.dto;

import com.atable.alcoholknowledge.model.WineInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class WineInfoDto {
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

    public WineInfoDto(){};
    public WineInfoDto (WineInfo wineInfo) {
        this.pk = wineInfo.getPk();
        this.nameEng = wineInfo.getNameEng();
        this.nameKor = wineInfo.getNameKor();
        this.price = wineInfo.getPrice();
        this.vintage = wineInfo.getVintage();
        this.store = wineInfo.getStore();
        this.region = wineInfo.getRegion();
        this.datePurchase = wineInfo.getDatePurchase();
        this.description = wineInfo.getDescription();
        this.sizeBottle = wineInfo.getSizeBottle();
    }
}
