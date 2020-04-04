package com.example.assignment2.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private static  LiveData<List<Word>> mAllWords;
    private static LiveData<List<Food>> mAllFoods;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
        mAllFoods = mWordDao.getAllFoods();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }
    public LiveData<List<Food>> getAllFoods() { return mAllFoods; }

    /** ----------------------------INSERT WORD----------------------- **/

    public void insertWord (String sWord, int ID) {
        Word word = new Word(sWord,ID);
        new insertWordAsyncTask(mWordDao).execute(word);
    }

    private static class insertWordAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;
        insertWordAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insertWord(params[0]);
            return null;
        }
    }
    /** ----------------------------Insert FOOD----------------------- **/
    public void insertFood (String sFood) {
        Food food = new Food(Food.current_ID++, sFood);
        new insertFoodAsyncTask(mWordDao).execute(food);
    }

    private static class insertFoodAsyncTask extends AsyncTask<Food, Void, Void> {
        private WordDao mAsyncTaskDao;
        insertFoodAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Food... params) {
            mAsyncTaskDao.insertFood(params[0]);
            return null;
        }
    }


    /** ----------------------------Insert All----------------------- **/
    public void insertAll (String word, String food) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(word);
        list.add(food);

        new insertAllAsyncTask(mWordDao).execute(list);
        return ;
    }

    private static class insertAllAsyncTask extends AsyncTask<List<String>, Void, Void> {
        private WordDao mAsyncTaskDao;
        insertAllAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<String>... params) {
            String food = params[0].get(1);
            String word = params[0].get(0);

            mAsyncTaskDao.insertFood(new Food(Food.current_ID++, food));
            Food foodObj = mAsyncTaskDao.getFoodID(food);

            mAsyncTaskDao.insertWord(new Word(word,foodObj.food_ID));
            return null;
        }
    }
}
