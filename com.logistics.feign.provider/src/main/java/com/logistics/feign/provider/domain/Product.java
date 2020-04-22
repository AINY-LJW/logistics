package com.logistics.feign.provider.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private String id;

    private String name;

    private BigDecimal price;

    private String classification;

    private BigDecimal buyprice;

    private Date createtime;

    private String place;

    private String url;

    private BigDecimal remainqty;

    private String mesurement;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification == null ? null : classification.trim();
    }

    public BigDecimal getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(BigDecimal buyprice) {
        this.buyprice = buyprice;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public BigDecimal getRemainqty() {
        return remainqty;
    }

    public void setRemainqty(BigDecimal remainqty) {
        this.remainqty = remainqty;
    }

    public String getMesurement() {
        return mesurement;
    }

    public void setMesurement(String mesurement) {
        this.mesurement = mesurement == null ? null : mesurement.trim();
    }
}