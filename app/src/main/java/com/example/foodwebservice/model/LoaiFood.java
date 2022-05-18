package com.example.foodwebservice.model;

public class LoaiFood {
    public int id;
    public String tenloaifood;
    public String hinhanhloaifood;

    public LoaiFood(int id, String tenloaifood, String hinhanhloaifood) {
        this.id = id;
        this.tenloaifood = tenloaifood;
        this.hinhanhloaifood = hinhanhloaifood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloaifood() {
        return tenloaifood;
    }

    public void setTenloaifood(String tenloaifood) {
        this.tenloaifood = tenloaifood;
    }

    public String getHinhanhloaifood() {
        return hinhanhloaifood;
    }

    public void setHinhanhloaifood(String hinhanhloaifood) {
        this.hinhanhloaifood = hinhanhloaifood;
    }
}
