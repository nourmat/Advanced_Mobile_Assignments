package com.example.assignment3.model.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("weather?")
    Call<WeatherResponse> getCountryJson(@Query("q") String name, @Query("appid") String APIID);
}
