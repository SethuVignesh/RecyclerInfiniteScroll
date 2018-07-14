package com.example.dell.noonacademy.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.dell.noonacademy.data.model.Movie;
import com.example.dell.noonacademy.data.model.SearchItem;

@Database(entities = {SearchItem.class, Movie.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract MoviesDao moviesDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "movies-database").allowMainThreadQueries().build();
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
