package com.example.LKserver;

import java.time.Instant;

import jakarta.validation.constraints.NotEmpty;

//data structure
public class LKserverData {

    public LKserverData(){ // empty constructor 

    }


    //constructor for json object.
    public LKserverData(String id, String userName, String lat, String longtitude, String url, String desc, String keyword, String dateTime){
        this.id=id;
        this.userName=userName;
        this.lat=lat;
        this.longtitude=longtitude;
        this.url=url;
        this.desc=desc;
        this.keyword=keyword;
        this.dateTime=dateTime;
    }
    private String id;

    @NotEmpty
    private String userName;

    private String lat;
    private String longtitude;
    private String url;
    private String desc;
    private String keyword;
    private String dateTime;

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    //set datetime of datetime object to current date item in iso format
    public void setDateTime(String dateTime) {
        this.dateTime = Instant.now().toString();
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//other fields

}
