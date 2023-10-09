package com.example.LKserver;

import jakarta.validation.constraints.NotEmpty;

//data structure
public class LKserverData {


    public String user_id;

    @NotEmpty
    public String[] keywords;

    @NotEmpty
    public String description;
    
    @NotEmpty
    public long lat;

    @NotEmpty
    public long lon;

    
    public String getUser_id() {
        return this.user_id;
    }

    
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String[] getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public long getLat() {
        return this.lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLon() {
        return this.lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }

    
    

    public LKserverData(String user_id, String[] keywords, String description, long lat, long lon){
        this.user_id=user_id;
        this.keywords=keywords;
        this.description=description;
        this.lat=lat;
        this.lon=lon;
    }


//other fields

}
