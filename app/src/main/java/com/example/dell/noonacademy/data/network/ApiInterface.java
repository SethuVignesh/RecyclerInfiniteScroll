package com.example.dell.noonacademy.data.network;

import com.example.dell.noonacademy.data.model.Movie;
import com.example.dell.noonacademy.data.model.SearchResponse;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    //http://www.omdbapi.com/?s=friends&apikey=yourkey
    @GET(".")
    Call<Movie> getMovieDetail(@Query("i") String id,@Query("apikey") String apiKey);

    //http://www.omdbapi.com/?i=tt3896198&apikey=yourkey
    @GET(".")
    Observable<SearchResponse> getMoviesList(@Query("page") String p,@Query("s") String s, @Query("apikey") String apiKey);


}

