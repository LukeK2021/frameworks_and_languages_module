package com.example.LKserver;

import java.util.LinkedList;

public class LKserverData {

    public LKserverData()
    {
        
    }
    
    public String id;
    public String username;
    public String lat;
    public String longtitude;
    public LinkedList<String> keywords;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongtitude() {
        return this.longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public LinkedList<String> getKeywords() {
        return this.keywords;
    }

    public void setKeywords(LinkedList<String> keywords) {
        this.keywords = keywords;
    }



}
