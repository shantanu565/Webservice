package com.shantanu.example.webservice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RetroAdapter extends RecyclerView.Adapter<RetroAdapter.ViewHolder> {
    private ArrayList<Posts> postsArrayList;

    public RetroAdapter(ArrayList<Posts> posts) {
        this.postsArrayList=posts;
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

    }

    @Override
    public int getItemCount() {
        return postsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_msg,tv_imageurl;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_msg = (TextView)view.findViewById(R.id.tv_message);
            //tv_imageurl = (TextView)view.findViewById(R.id.tv_api_level);

        }
    }

}
