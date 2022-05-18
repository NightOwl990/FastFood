package com.example.foodwebservice.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String taikhoan;
    private String matkhau;
    private String hoten;
    private String namsinh;
    private String diachi;
    private String email;
    private int sodienthoai;

    public User() {
    }

    public User(int id, String taikhoan, String matkhau, String hoten, String namsinh, String diachi, String email, int sodienthoai) {
        this.id = id;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.namsinh = namsinh;
        this.diachi = diachi;
        this.email = email;
        this.sodienthoai = sodienthoai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(int sodienthoai) {
        this.sodienthoai = sodienthoai;
    }
}
