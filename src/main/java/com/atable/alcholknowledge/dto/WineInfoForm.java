package com.atable.alcholknowledge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
