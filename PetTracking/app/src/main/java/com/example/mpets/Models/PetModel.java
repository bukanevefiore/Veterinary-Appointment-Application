package com.example.mpets.Models;

public class PetModel {

    public String petid;
    public String petresim;
    public String petisim;
    public String pettur;
    public String petcins;
    public boolean tf;

    public String getPetid() {
        return petid;
    }

    public String getPetresim() {
        return petresim;
    }

    public String getPetisim() {
        return petisim;
    }

    public String getPettur() {
        return pettur;
    }

    public String getPetcins() {
        return petcins;
    }

    public boolean isTf() {
        return tf;
    }

    @Override
    public String toString() {
        return "PetModel{" +
                "petid='" + petid + '\'' +
                ", petresim='" + petresim + '\'' +
                ", petisim='" + petisim + '\'' +
                ", pettur='" + pettur + '\'' +
                ", petcins='" + petcins + '\'' +
                ", tf=" + tf +
                '}';
    }
}
