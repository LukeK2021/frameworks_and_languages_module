package com.example.LKserver;

import jakarta.validation.constraints.NotEmpty;

//data structure
public class LKserverData {

    public LKserverData(){ // empty constructor 

    }



    public LKserverData(String id, String userName){
        this.id=id;
        this.userName=userName;
    }
    private String id;

    @NotEmpty
    private String userName;

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
