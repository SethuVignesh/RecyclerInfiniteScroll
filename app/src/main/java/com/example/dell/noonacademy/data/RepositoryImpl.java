package com.example.dell.noonacademy.data;

import android.app.Application;
import android.content.Context;

import com.example.dell.noonacademy.data.model.Movie;
import com.example.dell.noonacademy.data.model.SearchItem;
import com.example.dell.noonacademy.data.network.NetworkCalls;

import java.util.List;

public class RepositoryImpl implements Repository {

    public RepositoryImpl(Context context) {
        this.context = context;
    }

    Context context;


    @Override
    public List<SearchItem> getMovieList(String searchString) {

        //chekc for cache and local memory and then check in cloud
        return getMoviesListFromCloud(searchString);

    }

    @Override
    public Movie getDetails(String imdbID) {
        getDetailsFromCloud(imdbID, context);
        return null;
    }

    @Override
    public List<SearchItem> getBookMarkedList() {
        return getBookMarkedListFRomDB();
    }

    @Override
    public void saveBookMarkedList(List<SearchItem> list) {
        saveBookMarkedListToDB(list);

    }


    public List<SearchItem> getMoviesListFromDB(String searchString) {
        return null;
    }

    public List<SearchItem> getMoviesListFromCloud(String searchString) {
        return NetworkCalls.getInstance().getMovieList(searchString, context);
    }

    public Movie getDetailsFromDB(String imdbID) {
        return null;
    }

    public Movie getDetailsFromCloud(String imdbID, Context context) {
        return NetworkCalls.getInstance().getMovieDetails(imdbID, context);
    }

    public List<SearchItem> getMovieDetailFromCache(String searchString) {
        return null;
    }

    public Movie getDetailsFromCache(String imdbID) {
        return null;
    }

    public void saveBookMarkedListToDB(List<SearchItem> list) {

    }


    public List<SearchItem> getBookMarkedListFRomDB() {

        return null;
    }
}
