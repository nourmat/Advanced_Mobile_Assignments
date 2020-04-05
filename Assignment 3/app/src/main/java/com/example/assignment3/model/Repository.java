package com.example.assignment3.model;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.assignment3.model.retrofit.WeatherResponse;
import com.example.assignment3.model.retrofit.WeatherService;
import com.example.assignment3.model.room.Country;
import com.example.assignment3.model.room.CountryDao;
import com.example.assignment3.model.room.CountryRoomDatabase;

import java.util.ConcurrentModificationException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private CountryDao mCounrtyDao;
    private static LiveData<List<Country>> mAllCountries;

    /* Constructor */
    public Repository (Application application) {
        CountryRoomDatabase db = CountryRoomDatabase.getDatabase(application);
        mCounrtyDao = db.countryDao();
        mAllCountries = mCounrtyDao.getAllData();
    }

    /* ------------------ GET DATA --------------------*/
    public LiveData<List<Country>> getAllData() {
        return mAllCountries;
    }

    /* ----------------- INSERT DATA -------------------*/
    public void insertData (Country country) {
        new insertDataAsyncTask(mCounrtyDao).execute(country);
    }

    private static class insertDataAsyncTask extends AsyncTask<Country, Void, Void> {
        private CountryDao mAsyncTaskDao;
        insertDataAsyncTask(CountryDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Country... params) {
            mAsyncTaskDao.insertData(params[0]);
            return null;
        }
    }

    //------------------------------------------- Retrofit --------------------------------------------------

    private String BaseURL = "http://api.openweathermap.org/data/2.5/";
    private String APIID = "97aeab07cdeedb249555f1191d8e18ee";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    WeatherService weatherService = retrofit.create(WeatherService.class);

    /* ----------------------------- Retrieving the JSON object ------------------*/
    public void cacheWeatherCountryData (final String countryName){

        Call<WeatherResponse> call = weatherService.getCountryJson(countryName, APIID);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    String name = countryName;
                    float temp, min_temp, max_temp, humidity, pressure;

                    temp = weatherResponse.getTemp();
                    min_temp = weatherResponse.getMinTemp();
                    max_temp = weatherResponse.getMaxTemp();
                    humidity = weatherResponse.getHumidity();
                    pressure = weatherResponse.getPressure();

                    Country country = new Country(name, temp, min_temp, max_temp, humidity, pressure);

                    // insert on conflict in replaces to updates
                    insertData (country);
                } else {
                    Log.d ("tag","Failed to retrieve");
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
