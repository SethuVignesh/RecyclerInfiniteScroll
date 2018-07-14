package com.example.dell.noonacademy.data.network;

import android.content.Context;
import android.util.Log;

import com.example.dell.noonacademy.CONSTANTS;
import com.example.dell.noonacademy.data.model.Movie;
import com.example.dell.noonacademy.data.model.SearchResponse;
import com.example.dell.noonacademy.data.model.SearchItem;
import com.example.dell.noonacademy.presentation.view.MovieListActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCalls {
    private static NetworkCalls networkCalls = new NetworkCalls();

    private NetworkCalls() {

    }

    public static NetworkCalls getInstance() {
        return networkCalls;
    }

    List<SearchItem> movieList;
    Movie movie;

    public List<SearchItem> getMovieList(String searchString, Context cxt) {

        ApiInterface apiService =
                ApiClient.getRetrofit(cxt).create(ApiInterface.class);
//        Call<SearchResponse> call = apiService.getMoviesList(searchString, CONSTANTS.apiKey);
//        call.enqueue(new Callback<SearchResponse>() {
//            @Override
//            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
//
//                movieList = response.body().getSearch();
//            }
//
//            @Override
//            public void onFailure(Call<SearchResponse> call, Throwable t) {
//
//            }
//        });
        return movieList;

    }


    public Movie getMovieDetails(String imdbId, Context cxt) {
        ApiInterface apiService =
                ApiClient.getRetrofit(cxt).create(ApiInterface.class);
//        Call<Movie> call = apiService.getMovieDetail(imdbId, CONSTANTS.apiKey);
//        call.enqueue(new Callback<Movie>() {
//            @Override
//            public void onResponse(Call<Movie> call, Response<Movie> response) {
//                movie = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<Movie> call, Throwable t) {
//
//            }
//        });
        return movie;
    }
}
