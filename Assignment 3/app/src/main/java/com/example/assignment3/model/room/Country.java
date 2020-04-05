package com.example.assignment3.model.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "country_temp_table")
public class Country {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "country_name")
    public String country_name;

    @NonNull
    @ColumnInfo(name = "temp")
    public float temp;

    @NonNull
    @ColumnInfo(name = "min_temp")
    public float min_temp;

    @NonNull
    @ColumnInfo(name = "max_temp")
    public float max_temp;

    @NonNull
    @ColumnInfo(name = "humidity")
    public float humidity;

    @NonNull
    @ColumnInfo(name = "pressure")
    public float pressure;

    public Country(@NonNull String country_name, @NonNull float temp, @NonNull float min_temp, @NonNull float max_temp, @NonNull float humidity, @NonNull float pressure) {
        this.country_name = country_name;
        this.temp = temp;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.humidity = humidity;
        this.pressure = pressure;
    }
}
