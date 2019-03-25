package com.shantanu.example.webservice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private ArrayList<Posts> dataSet;
    private Context context;

    public PostAdapter(ArrayList<Posts> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.context=mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.rowlayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Posts posts = dataSet.get(position);
        holder.textView1.setText(posts.getName());
        holder.textView2.setText(posts.getMessage());
        //holder.imageView.setImageResource(dataSet.get(position).getProfileImage());

    }


    @Override
    public int getItemCount() {
        return dataSet.size();    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageview_post);
            this.textView1 = (TextView) itemView.findViewById(R.id.tv_name);
           this.textView2=(TextView)itemView.findViewById(R.id.tv_message);

        }
    }

}
