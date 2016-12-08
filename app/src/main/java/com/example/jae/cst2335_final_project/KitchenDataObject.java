package com.example.jae.cst2335_final_project;

/**
 * Class to store the values stored in the database
 */
public class KitchenDataObject {

    /**
     * appliance type
     */
    public String Type;

    /**
     * appliance name
     */
    public String Name;

    /**
     * last setting
     */
    public int Setting;

    /**
     * Loaded default constructor. class can not be called without type, name and setting
     * @param type
     * @param name
     * @param setting
     */
    public KitchenDataObject(String type, String name, int setting){
        setType(type);
        setName(name);
        setSetting(setting);
    }

    /**
     * setter for type
     * @param type
     */
    public void setType(String type) {
        Type = type;
    }

    /**
     * setter for name
     * @param name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * setter for setting
     * @param setting
     */
    public void setSetting(int setting) {
        Setting = setting;
    }

    /**
     * getter for type
     * @return Type
     */
    public String getType() {
        return Type;
    }

    /**
     * getter for name
     * @return Name
     */
    public String getName() {
        return Name;
    }

    /**
     * getter for Setting
     * @return Setting
     */
    public int getSetting() {
        return Setting;
    }
}
