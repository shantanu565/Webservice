package com.shantanu.example.webservice;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PostList {
    @SerializedName("posts")
    private ArrayList<Posts> posts=null;

    public ArrayList<Posts> getPosts(){
        return posts;
    }
    public void setPosts(ArrayList<Posts> posts){
        this.posts=posts;
    }
}
