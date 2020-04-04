package com.example.assignment2.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    public String word;

    @ForeignKey(entity = Food.class, parentColumns = "food_ID", childColumns = "food_ID", onDelete = ForeignKey.CASCADE)
    @NonNull
    @ColumnInfo(name = "word_food_ID")
    public int word_food_ID;


    public Word(@NonNull String word, @NonNull int word_food_ID) {
        this.word = word;
        this.word_food_ID = word_food_ID;
    }
    public String getWord(){return this.word;}
    public int getmID() { return word_food_ID; }
}
