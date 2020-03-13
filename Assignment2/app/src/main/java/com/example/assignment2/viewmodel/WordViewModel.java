package com.example.assignment2.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignment2.model.Food;
import com.example.assignment2.model.Word;
import com.example.assignment2.model.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;

    private LiveData<List<Word>> mAllWords;
    private LiveData<List<Food>> mAllFoods;

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
        mAllFoods = mRepository.getAllFoods();
    }

    public LiveData<List<Word>> getAllWords() { return mAllWords; }
    public LiveData<List<Food>> getAllFoods() { return mAllFoods; }

    public void insert(String word, String food) { mRepository.insertAll(word,food); }
}