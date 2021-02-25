package com.example.mpets.Models;

public class CampaignModel {

    private String resim;
    private Boolean tf;
    private String text;
    private String baslik;

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public Boolean isTf() {
        return tf;
    }

    public void setTf(Boolean tf) {
        this.tf = tf;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    @Override
    public String toString() {
        return "CampaignModel{" +
                "resim='" + resim + '\'' +
                ", tf=" + tf +
                ", text='" + text + '\'' +
                ", baslik='" + baslik + '\'' +
                '}';
    }
}
