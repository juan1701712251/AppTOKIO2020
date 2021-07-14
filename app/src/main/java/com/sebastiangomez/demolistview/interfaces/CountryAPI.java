package com.sebastiangomez.demolistview.interfaces;

import com.sebastiangomez.demolistview.model.Country;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface  CountryAPI {
    @GET("Country/{id}")
    public Call<Country> find(@Path("id") int id);
}
