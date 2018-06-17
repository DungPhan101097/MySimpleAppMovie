package com.example.lap10715.mysimpleappmovie.uis.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Loader;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lap10715.mysimpleappmovie.GlideApp;
import com.example.lap10715.mysimpleappmovie.R;
import com.example.lap10715.mysimpleappmovie.controllers.ApiClient;
import com.example.lap10715.mysimpleappmovie.controllers.ApiHTTPRequest;
import com.example.lap10715.mysimpleappmovie.controllers.components.AdapterComponent;
import com.example.lap10715.mysimpleappmovie.controllers.components.DaggerAdapterComponent;
import com.example.lap10715.mysimpleappmovie.controllers.loaders.MovieLoader;
import com.example.lap10715.mysimpleappmovie.controllers.modules.AdapterModule;
import com.example.lap10715.mysimpleappmovie.models.Movie;
import com.example.lap10715.mysimpleappmovie.models.MoviesResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Inject
    List<Movie> movieListCurrent;
    @Inject
    List<Movie>movieListNewLoad;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    ApiHTTPRequest apiHTTPRequest;

    private Context context;
    private int curPage = 1;


    public MyAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;

        AdapterComponent adapterComponent = DaggerAdapterComponent
                .builder()
                .adapterModule(new AdapterModule(context))
                .build();

        adapterComponent.inject(this);

        recyclerView.setLayoutManager(linearLayoutManager);

    }

    public void loadData(MovieLoader loader) {
        Call<MoviesResponse> call = loader.loadMovies(apiHTTPRequest, curPage);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                movieListCurrent = response.body().getResults();
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(context)
                .inflate(R.layout.layout_movie_item, parent, false);
        return new MyHolder(cardView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = movieListCurrent.get(position);
        if(holder instanceof MyHolder){
            MyHolder myHolder = (MyHolder)holder;
            myHolder.movieTitle.setText(movie.getTitle());
            myHolder.release.setText(movie.getReleaseDate());
            myHolder.rate.setText("Rated: "  + movie.getVoteAverage());

            String overview = movie.getOverview();
            if(!overview.isEmpty()){
                if(overview.length() > 100){
                    overview = overview.substring(0, 100) + " ...";
                }
                myHolder.overview.setText(overview);
            }

            String imagePath = ApiClient.IMAGE_PATH + movie.getPosterPath();
            GlideApp.with(context)
                    .load(Uri.parse(imagePath))
                    .placeholder(R.drawable.placeholder)
                    .into(myHolder.poster);

            // Set click lister for each item.

        }
    }

    @Override
    public int getItemCount() {
        return movieListCurrent.size();
    }

    class MyHolder extends  RecyclerView.ViewHolder{

        public ImageView poster;
        public TextView release;
        public TextView movieTitle;
        public TextView overview;
        public CardView card;
        public TextView rate;

        public MyHolder(View itemView) {
            super(itemView);
            card = (CardView)itemView;
            poster = itemView.findViewById(R.id.iv_poster);
            release = itemView.findViewById(R.id.tv_release);
            movieTitle = itemView.findViewById(R.id.tv_title);
            overview = itemView.findViewById(R.id.tv_overview);
            rate = itemView.findViewById(R.id.tv_rate);

        }
    }
}
