package com.example.dell.noonacademy.presentation.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.dell.noonacademy.NoonAcademyApplication;
import com.example.dell.noonacademy.data.RepositoryImpl;
import com.example.dell.noonacademy.data.model.SearchItem;
import com.example.dell.noonacademy.presentation.view.MovieListActivity;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private List<SearchItem> moviesList;

    public List<SearchItem> getMoviesList(String searchString) {
        if (moviesList == null) {
            moviesList = loadMovies(searchString);
        }
        return moviesList;
    }

    private List<SearchItem> loadMovies(String searchString) {
        // do something to load users
       return NoonAcademyApplication.repository.getMovieList(searchString);
    }
}