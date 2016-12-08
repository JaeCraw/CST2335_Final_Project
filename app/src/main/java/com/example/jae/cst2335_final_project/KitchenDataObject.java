package com.example.jae.cst2335_final_project;

/**
 * Created by Jae on 2016-12-07.
 */
public class KitchenDataObject {

    public String Type;
    public String Name;
    public int Setting;

    public KitchenDataObject(String type, String name, int setting){
        setType(type);
        setName(name);
        setSetting(setting);
    }

    public void setType(String type) {
        Type = type;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSetting(int setting) {
        Setting = setting;
    }

    public String getType() {
        return Type;
    }

    public String getName() {
        return Name;
    }

    public int getSetting() {
        return Setting;
    }
}
