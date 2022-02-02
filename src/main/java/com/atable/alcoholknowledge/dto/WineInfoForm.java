package com.atable.alcoholknowledge.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

//DTO
@Getter
@Setter
public class WineInfoForm {
    private String nameEng;
    private String nameKor;
    private Integer vintage;
    private Integer price;
    private Timestamp dateCreated;
    private Date datePurchase;
    private String description;
    private String store;
    private String region;
    private String sizeBottle;
}
