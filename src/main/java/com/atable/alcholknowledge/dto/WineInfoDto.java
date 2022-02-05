package com.atable.alcholknowledge.dto;

import com.atable.alcholknowledge.model.WineInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class WineInfoDto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;
    @Column(name="name_eng")
    private String nameEng;
    @Column(name="name_kor")
    private String nameKor;
    @Column(name="vintage")
    private Integer vintage;
    @Column(name="price")
    private Integer price;
    @Column(name="date_purchase") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date datePurchase;
    @Column(name="description")
    private String description;
    @Column(name="store")
    private String store;
    @Column(name="region")
    private String region;
    @Column(name="size_bottle")
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
