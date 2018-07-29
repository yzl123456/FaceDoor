package com.slowlife.facedoor.domain;

/**
 * Created by 泽林 on 2018/2/8.
 */

public class Guest {
    private String name;
    private String sex;
    private String phone;

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    private String visitTime;
    private String dueTime;
    private String carNumber;
    private String statu;

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    private Integer face;

    public Integer getFace() {
        return face;
    }

    public void setFace(Integer face) {
        this.face = face;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Guest(String name, String sex, String phone, String visitTime, String dueTime, String carNumber, Integer face,String statu) {
        this.statu=statu;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.visitTime = visitTime;
        this.dueTime = dueTime;
        this.carNumber = carNumber;
        this.face = face;
    }
}
