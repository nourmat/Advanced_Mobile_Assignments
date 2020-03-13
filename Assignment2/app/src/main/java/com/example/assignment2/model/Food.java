package com.example.assignment2.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "food_table")
public class Food {
    public static int current_ID = 0;

    @NonNull
    @ColumnInfo(name = "food_ID")
    public int food_ID;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "food")
    public String food;

    public Food(@NonNull int food_ID, @NonNull String food) {
        this.food_ID = food_ID;
        this.food = food;
    }

    public static void resetID() {current_ID = 0;}

    public String getWord(){return this.food;}
    public int getmID() { return this.food_ID; }
}
