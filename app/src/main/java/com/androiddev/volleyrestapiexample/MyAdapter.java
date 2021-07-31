package com.androiddev.volleyrestapiexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    //create an array to store you data of type PostModel.class
    PostModel[] data;

    public MyAdapter(PostModel[] data) {
        this.data = data;
    }

    //inflating the layout in the UI
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new MyViewHolder(view);
    }

    //combining the data and views together
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_id.setText(String.valueOf(data[position].getId()));
        holder.tv_title.setText(String.valueOf(data[position].getTitle()));
        holder.tv_body.setText(data[position].getBody());
        holder.tv_Userid.setText(String.valueOf(data[position].getUserId()));
    }
    //fetching the size or length of the PostModel[] data
    @Override
    public int getItemCount() {
        return data.length;
    }

    //fetching the view id from the single_row.xml
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_Userid;
        TextView tv_title;
        TextView tv_id;
        TextView tv_body;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id= itemView.findViewById(R.id.tv_id);
            tv_title= itemView.findViewById(R.id.tv_title);
            tv_Userid = itemView.findViewById(R.id.tv_user_id);
            tv_body =itemView.findViewById(R.id.tv_body);
        }
    }
}
