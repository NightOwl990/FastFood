package com.example.foodwebservice.model;

public class Giohang {
    public int idfd;
    public String tenfd;
    public long giafd;
    public String hinhfd;
    public int soluongfd;

    public Giohang(int idfd, String tenfd, long giafd, String hinhfd, int soluongfd) {
        this.idfd = idfd;
        this.tenfd = tenfd;
        this.giafd = giafd;
        this.hinhfd = hinhfd;
        this.soluongfd = soluongfd;
    }

    public Giohang(long giafd) {
        this.giafd = giafd;
    }

    public int getIdfd() {
        return idfd;
    }

    public void setIdfd(int idfd) {
        this.idfd = idfd;
    }

    public String getTenfd() {
        return tenfd;
    }

    public void setTenfd(String tenfd) {
        this.tenfd = tenfd;
    }

    public long getGiafd() {
        return giafd;
    }

    public void setGiafd(long giafd) {
        this.giafd = giafd;
    }

    public String getHinhfd() {
        return hinhfd;
    }

    public void setHinhfd(String hinhfd) {
        this.hinhfd = hinhfd;
    }

    public int getSoluongfd() {
        return soluongfd;
    }

    public void setSoluongfd(int soluongfd) {
        this.soluongfd = soluongfd;
    }
}
