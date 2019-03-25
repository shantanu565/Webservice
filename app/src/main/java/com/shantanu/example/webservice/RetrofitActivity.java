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
                    adapter=new RetroAdapter(data);
                    recyclerView.setAdapter(adapter);

                }


            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

                progressDialog.dismiss();
            }
        });



    }
    /*

    public void getPosts(){
        Retrofit retrofit= new Retrofit.Builder()
        .baseUrl(RequestInterface.Base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface=retrofit.create(RequestInterface.class);
        Call<List<Posts>> call=requestInterface.getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                List<Posts> post2=response.body();
                for (int i=0;i<post2.size();i++){
                    String p=post2.get(i).getName();
                    Toast.makeText(RetrofitActivity.this,p,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

                Toast.makeText(RetrofitActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        //loadJSON();
    }
    /*
    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
       // Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                adapter = new RetroAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
    */
}

