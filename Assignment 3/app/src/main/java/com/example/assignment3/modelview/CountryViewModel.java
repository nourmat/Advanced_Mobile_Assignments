package com.example.assignment3.modelview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.assignment3.model.Repository;
import com.example.assignment3.model.room.Country;
import com.example.assignment3.workerAPI;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CountryViewModel extends AndroidViewModel {

    public static final String WORKMANGERKEY = "key";
    private WorkManager mWorkManager;
    private Repository mRepository;
    private LiveData<List<Country>> mAllData;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllData = mRepository.getAllData();
        mWorkManager = WorkManager.getInstance(application);
    }

    public LiveData<List<Country>> getAllWords() { return mAllData; }

    public void insertData (Country country) { mRepository.insertData(country); }

    public void cacheFromTheInternet (String countryName) {
        mRepository.cacheWeatherCountryData(countryName);
    }

    public void startWorkManger (){
        workerAPI.setCountryViewModel(this);
        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(workerAPI.class, 15, TimeUnit.SECONDS)
                .setInputData(createInputData())
                .setConstraints(constraints)
                .build();

        mWorkManager.enqueue(request);
    }

    private Data createInputData() {
        Data.Builder builder = new Data.Builder();
        String[] cities = {"cairo","london", "paris"};
        builder.putStringArray(WORKMANGERKEY,cities);
        return builder.build();
    }
}
