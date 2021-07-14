package com.sebastiangomez.demolistview.model;

public class MedalByCountry {
    private int id_country;
    private String nameCountry;
    private int cantGoldMedal;
    private int cantSilverMedal;
    private int cantBronzeMedal;

    public MedalByCountry(int id_country,String nameCountry){
        this.id_country = id_country;
        this.nameCountry = nameCountry;
    }

    public void setId_country(int id_country) {
        this.id_country = id_country;
    }

    public int getId_country() {
        return id_country;
    }

    public void setCantBronzeMedal(int cantBronzeMedal) {
        this.cantBronzeMedal = cantBronzeMedal;
    }

    public void setCantGoldMedal(int cantGoldMedal) {
        this.cantGoldMedal = cantGoldMedal;
    }

    public void setCantSilverMedal(int cantSilverMedal) {
        this.cantSilverMedal = cantSilverMedal;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public int getCantBronzeMedal() {
        return cantBronzeMedal;
    }

    public int getCantGoldMedal() {
        return cantGoldMedal;
    }

    public int getCantSilverMedal() {
        return cantSilverMedal;
    }

    public String getNameCountry() {
        return nameCountry;
    }

}
