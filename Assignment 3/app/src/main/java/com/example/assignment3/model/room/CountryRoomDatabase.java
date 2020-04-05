package com.example.assignment3.model.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Country.class}, version = 1, exportSchema = false)
public abstract class CountryRoomDatabase extends RoomDatabase{

    public abstract CountryDao countryDao();
    private static CountryRoomDatabase INSTANCE;

    public static CountryRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CountryRoomDatabase.class) {
                if (INSTANCE == null) { //create database if doesn't exist
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CountryRoomDatabase.class, "country_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
        new RoomDatabase.Callback() {

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
                new PopulateDbAsync(INSTANCE).execute();
            }
        };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CountryDao mDao;

//        Country country = new Country("Cairo", 20, 12, 25, 54, 56);

        PopulateDbAsync(CountryRoomDatabase db) { mDao = db.countryDao(); }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAllData();
//            mDao.insertData(country);

            return null;
        }
    }
}
