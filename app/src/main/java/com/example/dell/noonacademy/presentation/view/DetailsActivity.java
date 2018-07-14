package com.example.dell.noonacademy.presentation.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.noonacademy.CONSTANTS;
import com.example.dell.noonacademy.R;
import com.example.dell.noonacademy.data.cache.PrefUtils;
import com.example.dell.noonacademy.data.model.Movie;
import com.example.dell.noonacademy.data.network.ApiClient;
import com.example.dell.noonacademy.data.network.ApiInterface;

import java.util.ArrayList;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    TextView title;

    TextView year;

    TextView genre;

    TextView director;

    TextView rating;

    TextView actors;

    ImageView imageView;
    ImageView favImageView;
    Button btnAdd, btnRemove;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_details);

        id = getIntent().getStringExtra("id");

        title = findViewById(R.id.tvTitle);
        year = findViewById(R.id.tvYear);
        genre = findViewById(R.id.tvGenre);
        director = findViewById(R.id.tvDirector);
        rating = findViewById(R.id.tvRating);
        actors = findViewById(R.id.tvActors);
        imageView = findViewById(R.id.ivPoster);
        favImageView = findViewById(R.id.imageViewFav);
        ArrayList<String> movieArrayList = PrefUtils.getFavList(DetailsActivity.this);
        if (movieArrayList.contains(id)) {
            favImageView.setVisibility(View.VISIBLE);
        } else {
            favImageView.setVisibility(View.INVISIBLE);
        }

        btnAdd = findViewById(R.id.buttonAdd);
        btnRemove = findViewById(R.id.buttonRemove);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> arrayList = PrefUtils.getFavList(DetailsActivity.this);
                arrayList.add(id);
                PrefUtils.saveFavList(arrayList, DetailsActivity.this);
                favImageView.setVisibility(View.VISIBLE);
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> arrayList = PrefUtils.getFavList(DetailsActivity.this);
                arrayList.remove(id);
                PrefUtils.saveFavList(arrayList, DetailsActivity.this);
                favImageView.setVisibility(View.INVISIBLE);
            }
        });

        ApiInterface apiService =
                ApiClient.getRetrofit(DetailsActivity.this).create(ApiInterface.class);
        Call<Movie> call = apiService.getMovieDetail(id, CONSTANTS.apiKey);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                title.setText(movie.getTitle());
                year.setText(movie.getYear());
                genre.setText(movie.getYear());
                director.setText(movie.getDirector());
                rating.setText(movie.getRated());
                actors.setText(movie.getActors());
                Glide.with(DetailsActivity.this).load(movie.getPoster()).into(imageView);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }
}
