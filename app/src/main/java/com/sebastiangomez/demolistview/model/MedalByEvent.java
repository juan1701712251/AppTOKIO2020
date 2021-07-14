package com.sebastiangomez.demolistview.model;

public class MedalByEvent {
    private int id;
    private int id_event;
    private int id_country;
    private String type_medal;

    public int getId() {
        return id;
    }

    public int getId_country() {
        return id_country;
    }

    public int getId_event() {
        return id_event;
    }

    public String getType_medal() {
        return type_medal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_country(int id_country) {
        this.id_country = id_country;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setType_medal(String type_medal) {
        this.type_medal = type_medal;
    }
}
