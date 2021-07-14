package com.sebastiangomez.demolistview.model;

public class SportEvent {
    private int id;
    private String name;
    private String type_event;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType_event() {
        return type_event;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }
}
