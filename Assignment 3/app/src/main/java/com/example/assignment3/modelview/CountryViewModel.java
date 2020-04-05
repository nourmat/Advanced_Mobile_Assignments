package com.example.assignment3.modelview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignment3.model.Repository;
import com.example.assignment3.model.room.Country;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    private Repository mRepository;
    private LiveData<List<Country>> mAllData;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllData = mRepository.getAllData();
    }

    public LiveData<List<Country>> getAllWords() { return mAllData; }

    public void insertData (Country country) { mRepository.insertData(country); }

    public void cacheFromTheInternet (String countryName) {
        mRepository.cacheWeatherCountryData(countryName);
    }
}
