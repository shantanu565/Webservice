package com.shantanu.example.webservice;

import android.app.ProgressDialog;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Posts> data;
    private ProgressDialog progressDialog;
    private RetroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        progressDialog=new ProgressDialog(RetrofitActivity.this);
        progressDialog.setMessage("Loaading...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        RequestInterface requestInterface=RetrofitClient.getReqInstance();
        Call<PostList> call=requestInterface.getMyPosts();

        call.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){
                    data=response.body().getPosts();
                    recyclerView = (RecyclerView)findViewById(R.id.rv1);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    adapter=new RetroAdapter(data,RetrofitActivity.this);
                    recyclerView.setAdapter(adapter);

                }


            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

                progressDialog.dismiss();
            }
        });



    }

}

