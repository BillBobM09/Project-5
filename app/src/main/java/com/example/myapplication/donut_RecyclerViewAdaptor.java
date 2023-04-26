package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class donut_RecyclerViewAdaptor extends RecyclerView.Adapter<donut_RecyclerViewAdaptor.MyViewHolder>{
    private final recyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<DonutModel> DonutModels;
   /////////////////////////////////////////////////
    public donut_RecyclerViewAdaptor(Context context, ArrayList<DonutModel> DonutModels,recyclerViewInterface recyclerViewInterface ){
    this.context = context;
    this.DonutModels=DonutModels;
    this.recyclerViewInterface = recyclerViewInterface;



    }
    @NonNull
    @Override
    public donut_RecyclerViewAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.each_donut, parent, false);
        return new donut_RecyclerViewAdaptor.MyViewHolder(view, recyclerViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull donut_RecyclerViewAdaptor.MyViewHolder holder, int position) {
    //assigning values to each row as them come on the screen
        holder.donutName.setText(DonutModels.get(position).getName());
        holder.price.setText(DonutModels.get(position).getPrice());
        holder.imageView.setImageResource(DonutModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        //total items
        return DonutModels.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

ImageView imageView;
TextView donutName, price;

        public MyViewHolder(@NonNull View itemView, recyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.donutImage);
            donutName = itemView.findViewById(R.id.donutname);
            price = itemView.findViewById(R.id.PriceView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int position =getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onClick(position);
                        }
                    }
                }
            });
        }
    }
}
