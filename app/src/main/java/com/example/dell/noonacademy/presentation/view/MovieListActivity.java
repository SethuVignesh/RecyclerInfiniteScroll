package com.example.dell.noonacademy.presentation.view;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.noonacademy.CONSTANTS;
import com.example.dell.noonacademy.R;
import com.example.dell.noonacademy.data.RepositoryImpl;
import com.example.dell.noonacademy.data.model.SearchItem;
import com.example.dell.noonacademy.data.model.SearchResponse;
import com.example.dell.noonacademy.data.network.ApiClient;
import com.example.dell.noonacademy.data.network.ApiInterface;
import com.example.dell.noonacademy.presentation.viewmodel.MovieListViewModel;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private static final String TAG = MovieListActivity.class.getSimpleName();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.search)
    EditText search;
    MoviesAdapter mAdapter;
    List<SearchItem> movieList = new ArrayList<>();

    LinearLayoutManager mLayoutManager;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    int pageNo = 1;
    String searchString = "friend";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

//        MovieListViewModel movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        ButterKnife.bind(this);
//        movieList=movieListViewModel.getMoviesList(search.getText().toString());


        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MoviesAdapter(movieList, MovieListActivity.this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            pageNo = pageNo + 1;
                            getResponse(pageNo, searchString);
                            //Do pagination.. i.e. fetch new data
                        }
                    }
                }
            }
        });

        getResponse(2
                , searchString);


        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchString = s.toString();
                if (movieList != null)
                    movieList.clear();
                getResponse(1, searchString);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    private void getResponse(int page, String searchString) {
        if (searchString == null || searchString.length() == 0) {
            searchString = "friend";
        }
        ApiInterface apiService =
                ApiClient.getRetrofit(MovieListActivity.this).create(ApiInterface.class);
        Observable<SearchResponse> observable = apiService.getMoviesList(page + "", searchString, CONSTANTS.apiKey);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SearchResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchResponse searchResponse) {
                List<SearchItem> arrayList = searchResponse.getSearch();
                if (arrayList != null)
                    movieList.addAll(arrayList);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                loading = true;

                mAdapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        Intent intent = new Intent(MovieListActivity.this, DetailsActivity.class);
        intent.putExtra("id", movieList.get(position).getImdbID());
        startActivity(intent);

    }

}
