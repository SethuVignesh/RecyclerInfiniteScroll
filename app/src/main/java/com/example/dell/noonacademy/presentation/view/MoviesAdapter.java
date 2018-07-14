package com.example.dell.noonacademy.presentation.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.noonacademy.R;
import com.example.dell.noonacademy.data.cache.PrefUtils;
import com.example.dell.noonacademy.data.model.SearchItem;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<SearchItem> moviesList = new ArrayList<>();
    private static RecyclerViewClickListener itemListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, year;
        public ImageView poster, fav;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            poster = (ImageView) view.findViewById(R.id.imageView);
            year = (TextView) view.findViewById(R.id.year);
            fav = (ImageView) view.findViewById(R.id.ivFav);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getPosition());
        }
    }

    private Context cxt;
    ArrayList<String> movieArrayList;

    public MoviesAdapter(List<SearchItem> moviesList, Context cxt) {
        this.moviesList = moviesList;
        if (moviesList == null) moviesList = new ArrayList<>();
        this.cxt = cxt;
        this.itemListener = (RecyclerViewClickListener) cxt;
//        movieArrayList = PrefUtils.getFavList(cxt);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        movieArrayList = PrefUtils.getFavList(cxt);
        SearchItem movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        Glide.with(cxt).load(movie.getPoster()).into(holder.poster);
        holder.year.setText(movie.getYear());
        if (movieArrayList.contains(movie.getImdbID())) {
            holder.fav.setVisibility(View.VISIBLE);
        } else {
            holder.fav.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        if (moviesList == null) return 0;
        else
            return moviesList.size();
    }
}