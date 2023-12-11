package com.example.ugm.models;

public class resep {
    private String namaResep, gambar;

    public resep() {

    }

    public resep(String namaResep, String gambar) {
        this.namaResep = namaResep;
        this.gambar = gambar;
    }

    public String getNamaResep() {
        return namaResep;
    }
    public void setNamaResep(String namaResep) {
        this.namaResep = namaResep;
    }

    public String getgambar() {
        return gambar;
    }

    public void setgambar() {
        this.gambar = gambar;
    }
}
