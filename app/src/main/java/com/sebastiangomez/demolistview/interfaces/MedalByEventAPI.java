package com.sebastiangomez.demolistview.interfaces;

import com.sebastiangomez.demolistview.model.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MedalByEventAPI {
    @GET("medal_by_event")
    public Call<Root> getAll();
}
