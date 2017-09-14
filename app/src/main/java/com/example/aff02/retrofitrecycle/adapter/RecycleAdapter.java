package com.example.aff02.retrofitrecycle.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aff02.retrofitrecycle.R;
import com.example.aff02.retrofitrecycle.activity.ViewDetails;
import com.example.aff02.retrofitrecycle.model.RetrofitModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AFF02 on 12-Sep-17.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    public List<RetrofitModel> retrofitModelList;
    public Context context;
    public RecyclerView recyclerView;
    private static final String PREFS = "Prefs";


    public RecycleAdapter(List<RetrofitModel> retrofitModelList, Context context) {

        this.retrofitModelList = retrofitModelList;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView proimage;
        public TextView proname,prodesc;
        public CardView cardView;

       // public RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);

            proimage = (ImageView)itemView.findViewById(R.id.proimage);
            proname = (TextView)itemView.findViewById(R.id.proname);
            prodesc = (TextView)itemView.findViewById(R.id.prodesc);
            cardView = (CardView)itemView.findViewById(R.id.cardview);
            //recyclerView = (RecyclerView)itemView.findViewById(R.id.recyclerview);
        }
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecycleAdapter.ViewHolder holder, int position) {

        final RetrofitModel model = retrofitModelList.get(position);
        holder.proname.setText(model.getProdname());
        holder.prodesc.setText(model.getBriefDesc());

        Picasso.with(context)
                .load("http://www.silvergiftz.com/images/product/" + model.getProdImage())
                .into(holder.proimage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RetrofitModel retrofitModel = retrofitModelList.get()
                Toast.makeText(context,"You Selected : "+model.getProdname(),Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context,ViewDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Proname",model.getProdname());
                intent.putExtra("Prodesc",model.getBriefDesc());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return retrofitModelList.size();
    }


}
