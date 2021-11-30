package com.atable.alcholknowledge.model;

import java.sql.Date;

public class CorkageStore {
    private long id;
    private String name;
    private String addr;
    private String area;
    private double longitude;
    private double latitude;
    private String desc;
    private int isChecked;
    private Date dateUpdate;
    private String website;
    private String instagram;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
