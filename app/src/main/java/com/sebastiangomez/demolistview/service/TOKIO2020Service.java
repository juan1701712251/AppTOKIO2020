package com.sebastiangomez.demolistview.service;

import android.util.Log;

import com.sebastiangomez.demolistview.interfaces.CountryAPI;
import com.sebastiangomez.demolistview.interfaces.MedalByEventAPI;
import com.sebastiangomez.demolistview.model.Country;
import com.sebastiangomez.demolistview.model.Root;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TOKIO2020Service {
    private final String URL_SERVICE = "http://universidaddecaldas.apps.dreamfactory.com/api/v2/mysql/_table/";
    private final String API_KEY = "5ad51294309eec6cbfa132c695e08c5460f8f44bdb961342a62586617972f3e6";

    public void requestCountryData(int id, OnResponse delegate){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("X-DreamFactory-API-Key", API_KEY).build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL_SERVICE).client(httpClient.build()).build();

        CountryAPI countryAPI = retrofit.create(CountryAPI.class);

        Call<Country> call = countryAPI.find(id);

        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                try{
                    if(response.isSuccessful()){
                        Country c = response.body();
                        Log.d("Country ",c.getName());
                        if (delegate != null){
                            delegate.run(200,c);
                        }
                    }
                }catch(Exception ex){
                    Log.d("Error: ","Error obteniendo datos");
                }
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Log.e("Error: ",t.getMessage());
            }
        });
    }

    public void requestMedalsByEventData( OnResponseMedalByEvent delegate){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("X-DreamFactory-API-Key", API_KEY).build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL_SERVICE).client(httpClient.build()).build();

        MedalByEventAPI medalByEventAPI = retrofit.create(MedalByEventAPI.class);

        Call<Root> call = medalByEventAPI.getAll();

        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                try{
                    if(response.isSuccessful()){
                        Root r = response.body();
                        if (delegate != null){
                            delegate.run(200,r);
                        }
                    }
                }catch(Exception ex){
                    Log.d("Error: ","Error obteniendo datos");
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.e("Error: ",t.getMessage());
            }
        });
    }

    public interface OnResponse{
        public abstract void run(int statusCode,Country root);
    }
    public interface OnResponseMedalByEvent{
        public abstract void run(int statusCode,Root root);
    }
}
