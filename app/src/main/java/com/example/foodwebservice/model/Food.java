package com.example.foodwebservice.model;

import com.example.foodwebservice.MainActivity;

import java.io.Serializable;

public class Food implements Serializable {
    public int id;
    public String tenfood;
    public Integer giafood;
    public String hinhanhfood;
    public String motafood;
    public int idfood;
    public int giacufood;

    public Food() {
    }

    public Food(String tenfood, Integer giafood, String hinhanhfood, String motafood) {
        this.tenfood = tenfood;
        this.giafood = giafood;
        this.hinhanhfood = hinhanhfood;
        this.motafood = motafood;
    }

    public Food(int id, String tenfood, Integer giafood, String hinhanhfood, String motafood, int idfood) {
        this.id = id;
        this.tenfood = tenfood;
        this.giafood = giafood;
        this.hinhanhfood = hinhanhfood;
        this.motafood = motafood;
        this.idfood = idfood;
    }

    public Food(int id, String tenfood, Integer giafood, String hinhanhfood, String motafood, int idfood, int giacufood) {
        this.id = id;
        this.tenfood = tenfood;
        this.giafood = giafood;
        this.hinhanhfood = hinhanhfood;
        this.motafood = motafood;
        this.idfood = idfood;
        this.giacufood = giacufood;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenfood() {
        return tenfood;
    }

    public void setTenfood(String tenfood) {
        this.tenfood = tenfood;
    }

    public Integer getGiafood() {
        return giafood;
    }

    public void setGiafood(Integer giafood) {
        this.giafood = giafood;
    }

    public String getHinhanhfood() {
        return hinhanhfood;
    }

    public void setHinhanhfood(String hinhanhfood) {
        this.hinhanhfood = hinhanhfood;
    }

    public String getMotafood() {
        return motafood;
    }

    public void setMotafood(String motafood) {
        this.motafood = motafood;
    }

    public int getIdfood() {
        return idfood;
    }

    public void setIdfood(int idfood) {
        this.idfood = idfood;
    }

    public int getGiacufood() {
        return giacufood;
    }

    public void setGiacufood(int giacufood) {
        this.giacufood = giacufood;
    }
}
