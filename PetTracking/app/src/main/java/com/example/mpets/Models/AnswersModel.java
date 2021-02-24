package com.example.mpets.Models;

public class AnswersModel {

    public String soru;
    public String cevap;
    public String cevapid;
    public String soruid;
    public Boolean tf;

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getCevap() {
        return cevap;
    }

    public void setCevap(String cevap) {
        this.cevap = cevap;
    }

    public String getCevapid() {
        return cevapid;
    }

    public void setCevapid(String cevapid) {
        this.cevapid = cevapid;
    }

    public String getSoruid() {
        return soruid;
    }

    public void setSoruid(String soruid) {
        this.soruid = soruid;
    }

    public Boolean isTf() {
        return tf;
    }

    public void setTf(Boolean tf) {
        this.tf = tf;
    }

    @Override
    public String toString() {
        return "AnswersModel{" +
                "soru='" + soru + '\'' +
                ", cevap='" + cevap + '\'' +
                ", cevapid='" + cevapid + '\'' +
                ", soruid='" + soruid + '\'' +
                ", tf=" + tf +
                '}';
    }
}
