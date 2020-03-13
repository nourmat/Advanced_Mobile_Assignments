package com.example.assignment2.model;

import android.graphics.ColorSpace;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFood(Food food);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWord(Word word);

    @Query("DELETE FROM word_table")
    void deleteAllWords();

    @Query("DELETE FROM food_table")
    void deleteAllFoods();

    @Query("SELECT * from food_table WHERE food IS :food_name")
    Food getFoodID(String food_name);

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    @Query("SELECT * from food_table ORDER BY food_ID ASC")
    LiveData<List<Food>> getAllFoods();
}