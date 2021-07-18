package com.sebastiangomez.demolistview.model;

import java.util.ArrayList;

public class Root {
    private ArrayList<MedalByEvent> resource;

    public ArrayList<MedalByEvent> getResource() {
        return resource;
    }

    public void setCountries(ArrayList<MedalByEvent> resource) {
        this.resource = resource;
    }

    public Object clone(){
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
}
