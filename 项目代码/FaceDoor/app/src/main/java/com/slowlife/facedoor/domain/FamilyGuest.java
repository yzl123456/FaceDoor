package com.slowlife.facedoor.domain;

import android.content.Intent;

/**
 * Created by 泽林 on 2018/2/8.
 */

public class FamilyGuest {
    private String name;
    private String sex;
    private String relation;
    private String phone;
    private String IDCard;
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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public FamilyGuest(String name, String sex, String relation, String phone, String IDCard,Integer face) {
        this.name = name;
        this.sex = sex;
        this.relation = relation;
        this.phone = phone;
        this.IDCard = IDCard;
        this.face=face;

    }

    @Override
    public String toString() {
        return "FamilyGuest{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", relation='" + relation + '\'' +
                ", phone='" + phone + '\'' +
                ", IDCard='" + IDCard + '\'' +
                '}';
    }
}
