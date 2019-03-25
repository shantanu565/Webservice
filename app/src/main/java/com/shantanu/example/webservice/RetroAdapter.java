package com.shantanu.example.webservice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RetroAdapter extends RecyclerView.Adapter<RetroAdapter.ViewHolder> {
    private static final String TAG = "RetroAdapter";
    private ArrayList<Posts> postsArrayList;
    private Context context;

    public RetroAdapter(ArrayList<Posts> posts,Context context) {
        this.postsArrayList=posts;
        this.context=context;
    }

    @Override
    public RetroAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowlayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RetroAdapter.ViewHolder viewHolder, int i) {

        Posts posts=postsArrayList.get(i);
        viewHolder.tv_name.setText(postsArrayList.get(i).getName());
        viewHolder.tv_msg.setText(postsArrayList.get(i).getMessage());
        String u=postsArrayList.get(i).getProfileImage();
        Log.i(TAG, "onBindViewHolder: "+u);
        Toast.makeText(context,u,Toast.LENGTH_SHORT).show();
        Glide.with(context).load(u).into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return postsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_msg,tv_imageurl;
        private ImageView imageView;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_msg = (TextView)view.findViewById(R.id.tv_message);
            imageView=(ImageView)view.findViewById(R.id.imageview_post);
            //tv_imageurl = (TextView)view.findViewById(R.id.tv_api_level);


        }
    }

}
