package com.shantanu.example.webservice;

import com.google.gson.annotations.SerializedName;

public class Posts {

    @SerializedName("name")
    private String name;
    @SerializedName("message")
    private String message;
    @SerializedName("profileImage")
    private String profileImage;

    public Posts(){

    }
    public Posts(String name,String message,String profileImage){
        this.name=name;
        this.message=message;
        this.profileImage=profileImage;
    }

    public String getName(){
        return name;
    }
    public String getMessage(){
        return message;
    }
    public String getProfileImage(){
        return profileImage;
    }

    public void setName(String name){
        this.name=name;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public void setProfileImage(String profileImage){
        this.profileImage=profileImage;
    }

}
