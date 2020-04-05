package com.example.assignment3.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.assignment3.R;
import com.example.assignment3.model.room.Country;
import com.example.assignment3.modelview.CountryViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CountryViewModel mCountryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CountryListAdapter adapter = new CountryListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCountryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);

        mCountryViewModel.getAllWords().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(@Nullable final List<Country> Countries) {
                // Update the cached copy of the words in the adapter.
                adapter.setCountries(Countries);
            }
        });

        mCountryViewModel.cacheFromTheInternet ("london");
        mCountryViewModel.cacheFromTheInternet ("cairo");
        mCountryViewModel.cacheFromTheInternet ("paris");
    }
}
