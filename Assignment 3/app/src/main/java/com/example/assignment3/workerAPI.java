package com.example.assignment3;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.assignment3.modelview.CountryViewModel;

public class workerAPI extends Worker{

    private static CountryViewModel mCountryViewModel;

    public workerAPI(
            @NonNull Context appContext,
            @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        String[] cities = getInputData().getStringArray(CountryViewModel.WORKMANGERKEY);

        try {
            for (String city : cities) mCountryViewModel.cacheFromTheInternet(city);
            return Result.success();
        } catch (Throwable throwable){
            return Result.failure();
        }
    }

    public static void setCountryViewModel(CountryViewModel model) {
        mCountryViewModel = model;
    }
}
