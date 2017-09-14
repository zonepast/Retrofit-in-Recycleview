package com.example.aff02.retrofitrecycle.api;

import com.example.aff02.retrofitrecycle.model.RetrofitModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by AFF02 on 12-Sep-17.
 */

public interface APInterface {

    String BASE_URL = "http://27.109.20.118/SilverPixelz/api/categories/";

    @GET("4/products")
    Call<List<RetrofitModel>> getData();
}
