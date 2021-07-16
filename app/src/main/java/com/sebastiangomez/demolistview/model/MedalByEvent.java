package com.sebastiangomez.demolistview.model;

public class MedalByEvent {
    private int id;
    private int id_event;
    private int id_country;
    private String type_medal;
    private Country Country_by_id_country;
    private SportEvent sport_event_by_id_event;

    public int getId() {
        return id;
    }

    public int getId_country() {
        return id_country;
    }

    public Country getCountry_by_id_country() {
        return Country_by_id_country;
    }

    public SportEvent getSport_event_by_id_event() {
        return sport_event_by_id_event;
    }

    public void setCountry_by_id_country(Country country_by_id_country) {
        Country_by_id_country = country_by_id_country;
    }

    public void setSport_event_by_id_event(SportEvent sport_event_by_id_event) {
        this.sport_event_by_id_event = sport_event_by_id_event;
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
