package com.example.dell.noonacademy.data;

import com.example.dell.noonacademy.data.model.Movie;
import com.example.dell.noonacademy.data.model.SearchItem;

import java.util.List;

public interface Repository {
    public List<SearchItem> getMovieList(String searchString);
    public Movie getDetails(String imdbID);

    public List<SearchItem> getBookMarkedList();
    public void saveBookMarkedList(List<SearchItem> movieList);

            //crud

}
