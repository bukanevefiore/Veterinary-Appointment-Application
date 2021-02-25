package com.example.mpets.Models;

public class AsiModel {

    public String petisim;
    public String pettur;
    public String petcins;
    public String petresim;
    public String asitarih;
    public String asiisim;
    public boolean tf;

    public String getPettur() {
        return pettur;
    }

    public void setPettur(String pettur) {
        this.pettur = pettur;
    }

    public String getPetcins() {
        return petcins;
    }

    public void setPetcins(String petcins) {
        this.petcins = petcins;
    }

    public String getPetresim() {
        return petresim;
    }

    public void setPetresim(String petresim) {
        this.petresim = petresim;
    }

    public String getAsitarih() {
        return asitarih;
    }

    public void setAsitarih(String asitarih) {
        this.asitarih = asitarih;
    }

    public String getAsiisim() {
        return asiisim;
    }

    public void setAsiisim(String asiisim) {
        this.asiisim = asiisim;
    }

    public boolean isTf() {
        return tf;
    }

    public void setTf(boolean tf) {
        this.tf = tf;
    }

    public String getPetisim() {
        return petisim;
    }

    public void setPetisim(String petisim) {
        this.petisim = petisim;
    }

    @Override
    public String toString() {
        return "AsiModel{" +
                "petisim='" + petisim + '\'' +
                ", pettur='" + pettur + '\'' +
                ", petcins='" + petcins + '\'' +
                ", petresim='" + petresim + '\'' +
                ", asitarih='" + asitarih + '\'' +
                ", asiisim='" + asiisim + '\'' +
                ", tf=" + tf +
                '}';
    }
}
