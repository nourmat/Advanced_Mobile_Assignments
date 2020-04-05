package com.example.assignment3.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(Country country);

    @Query("DELETE FROM country_temp_table")
    void deleteAllData();

    @Query("SELECT * from country_temp_table ORDER BY country_name ASC")
    LiveData<List<Country>> getAllData();
}