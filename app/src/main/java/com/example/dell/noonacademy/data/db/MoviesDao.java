package com.example.dell.noonacademy.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.dell.noonacademy.data.model.Movie;
import com.example.dell.noonacademy.data.model.SearchItem;

import java.util.List;


@Dao
public interface MoviesDao {
    @Query("SELECT * FROM Movies")
    List<Movie> getAll();

    @Query("SELECT COUNT(*) from Movies")
    int countItems();

    @Insert
    void insertAll(SearchItem... items);

    @Delete
    void delete(SearchItem item);
}
