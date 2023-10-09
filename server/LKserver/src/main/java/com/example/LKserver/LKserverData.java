package com.example.LKserver;

import jakarta.validation.constraints.NotEmpty;

//data structure
public class LKserverData {

    public LKserverData(){ // empty constructor 

    }



    public LKserverData(String id, String userName, String lat, String longtitude, String url, String desc, String[] keyword){
        this.id=id;
        this.userName=userName;
        this.lat=lat;
        this.longtitude=longtitude;
        this.url=url;
        this.desc=desc;
        this.keyword=keyword;
    }
    private String id;

    @NotEmpty
    private String userName;

    private String lat;
    private String longtitude;
    private String url;
    private String desc;
    private String[] keyword;

    public String[] getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String[] keyword) {
        this.keyword = keyword;
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
