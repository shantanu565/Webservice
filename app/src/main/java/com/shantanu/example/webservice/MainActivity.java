package com.shantanu.example.webservice;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class MainActivity extends AppCompatActivity {
    Button btnFetch,btnQuestwo,btnQuesThree;
    RelativeLayout relativeLayout;
    private String imageurl="https://i.stack.imgur.com/7vMmx.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout=(RelativeLayout)findViewById(R.id.rel_lay);
        btnFetch=(Button)findViewById(R.id.btn_fetch);
        btnQuesThree=(Button)findViewById(R.id.btn_three);
        //btnQuesThree=(Button)findViewById(R.id.btn_three);
        btnQuestwo=(Button)findViewById(R.id.btn_questwo);
        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(MainActivity.this).load(imageurl).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
                            relativeLayout.setBackground(resource);
                        }
                    }
                });


            }
        });
        btnQuestwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,RetrofitActivity.class);
                startActivity(i);

            }
        });

    }
    public void thirdfunc(View view){
        Intent i8=new Intent(MainActivity.this,HttpActivity.class);
        startActivity(i8);
    }
}
