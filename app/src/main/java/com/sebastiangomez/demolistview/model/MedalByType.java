package com.sebastiangomez.demolistview.model;

public class MedalByType {
    private int id_country;
    private int id_event;
    private String name;
    private int cantGoldMedal;
    private int cantSilverMedal;
    private int cantBronzeMedal;

    public MedalByType(int id_type, String name){
        this.id_country = id_type;
        this.name = name;
        this.id_event = id_type;
        this.cantGoldMedal = 0;
        this.cantSilverMedal = 0;
        this.cantBronzeMedal = 0;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setId_country(int id_country) {
        this.id_country = id_country;
    }

    public int getId_country() {
        return id_country;
    }

    public void setCantBronzeMedal(int cantBronzeMedal) {
        this.cantBronzeMedal += cantBronzeMedal;
    }

    public void setCantGoldMedal(int cantGoldMedal) {
        this.cantGoldMedal += cantGoldMedal;
    }

    public void setCantSilverMedal(int cantSilverMedal) {
        this.cantSilverMedal += cantSilverMedal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCantBronzeMedal() {
        return cantBronzeMedal;
    }

    public int getCantGoldMedal() {
        return cantGoldMedal;
    }

    public int getTotalMedal() {
        return cantGoldMedal+cantBronzeMedal+cantSilverMedal;
    }

    public int getCantSilverMedal() {
        return cantSilverMedal;
    }

    public String getName() {
        return name;
    }

}
