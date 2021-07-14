package com.sebastiangomez.demolistview.model;

import java.util.List;

public class Root {
    private List<MedalByEvent> resource;

    public List<MedalByEvent> getResource() {
        return resource;
    }

    public void setCountries(List<MedalByEvent> resource) {
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
