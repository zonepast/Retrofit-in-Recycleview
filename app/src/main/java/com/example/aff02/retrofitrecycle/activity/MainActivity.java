package com.example.aff02.retrofitrecycle.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.aff02.retrofitrecycle.R;
import com.example.aff02.retrofitrecycle.adapter.RecycleAdapter;
import com.example.aff02.retrofitrecycle.api.APInterface;
import com.example.aff02.retrofitrecycle.model.RetrofitModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BASE_URL ="http://27.109.20.118/SilverPixelz/api/categories/";
    public RecyclerView recyclerView;
    public List<RetrofitModel> modellist;
    RetrofitModel retrofitmodel;
    RecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Select Particular Gift");

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        modellist = new ArrayList<>();
        //adapter = new RecycleAdapter(modellist,getApplicationContext());
        loadJson();

    }
    private void loadJson() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APInterface apiinterface = retrofit.create(APInterface.class);

        Call<List<RetrofitModel>> call = apiinterface.getData();

        call.enqueue(new Callback<List<RetrofitModel>>() {
            @Override
            public void onResponse(Call<List<RetrofitModel>> call, Response<List<RetrofitModel>> response) {

                //List<RetrofitModel> modellist = response.body();

                for (int i = 0; i<response.body().size();i++)
                {
                    retrofitmodel = new RetrofitModel();
                    retrofitmodel.setProdname(response.body().get(i).getProdname());
                    //Log.e("Name",response.body().get(i).getProdname());
                    retrofitmodel.setBriefDesc(response.body().get(i).getBriefDesc());
                    //Log.e("BriefDesc",response.body().get(i).getBriefDesc());
                    retrofitmodel.setProdImage(response.body().get(i).getProdImage());
                    //Log.e("Image",response.body().get(i).getProdImage());
                    modellist.add(retrofitmodel);
                }

                RecycleAdapter recyclerAdapter = new RecycleAdapter(modellist,getApplicationContext());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<RetrofitModel>> call, Throwable t) {

                Toast.makeText(MainActivity.this,"ERROR : - (",Toast.LENGTH_LONG).show();
            }
        });
    }
}
