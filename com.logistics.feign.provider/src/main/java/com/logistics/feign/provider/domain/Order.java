package com.logistics.feign.provider.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String id;

    private String coorid;

    private String pid;

    private String place;

    private BigDecimal money;

    private Date createtime;

    private Date sendtime;

    private Byte endflag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCoorid() {
        return coorid;
    }

    public void setCoorid(String coorid) {
        this.coorid = coorid == null ? null : coorid.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Byte getEndflag() {
        return endflag;
    }

    public void setEndflag(Byte endflag) {
        this.endflag = endflag;
    }
}