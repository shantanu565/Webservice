package com.shantanu.example.webservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
   // String Base="https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/";

    @GET("v2/posts.json")
    Call<PostList> getMyPosts();
}
