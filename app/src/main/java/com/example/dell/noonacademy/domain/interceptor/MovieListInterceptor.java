package com.example.dell.noonacademy.domain.interceptor;

import com.example.dell.noonacademy.data.model.SearchItem;

import java.util.List;

public interface MovieListInterceptor {
    public List<SearchItem> getMoviesList(String searchString);
}
