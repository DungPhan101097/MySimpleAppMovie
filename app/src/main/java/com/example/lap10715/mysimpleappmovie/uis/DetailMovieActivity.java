package com.example.lap10715.mysimpleappmovie.uis;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lap10715.mysimpleappmovie.GlideApp;
import com.example.lap10715.mysimpleappmovie.R;
import com.example.lap10715.mysimpleappmovie.controllers.ApiClient;
import com.example.lap10715.mysimpleappmovie.controllers.ApiHTTPRequest;
import com.example.lap10715.mysimpleappmovie.controllers.components.AdapterComponent;
import com.example.lap10715.mysimpleappmovie.controllers.components.DaggerAdapterComponent;
import com.example.lap10715.mysimpleappmovie.controllers.modules.AdapterModule;
import com.example.lap10715.mysimpleappmovie.models.Movie;
import com.example.lap10715.mysimpleappmovie.models.MoviesResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieActivity extends AppCompatActivity {
    @Inject
    ApiHTTPRequest apiHTTPRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if(intent!= null){
            Integer id = intent.getExtras().getInt(MyMovieItemClickListenner.ID_MOVIE);

            AdapterComponent component = DaggerAdapterComponent
                    .builder()
                    .adapterModule(new AdapterModule(this))
                    .build();

            component.inject(this);

            Call<Movie> movieCall = apiHTTPRequest.getMovieDetails(id, ApiClient.API_KEY);
            movieCall.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    Movie movie = response.body();
                    if(movie != null){
                        setTitle(movie.getTitle());

                        TextView tvTitle = findViewById(R.id.detail_title);
                        TextView tvRelease = findViewById(R.id.detail_release);
                        TextView tvRated = findViewById(R.id.detail_rate);
                        TextView tvOverview = findViewById(R.id.detail_overview);
                        ImageView imPoster = findViewById(R.id.detail_poster);

                        tvTitle.setText(movie.getTitle());
                        tvOverview.setText(movie.getOverview());
                        tvRated.setText("Rate: " + movie.getVoteAverage());
                        tvRelease.setText("Release: " + movie.getReleaseDate());
                        String pathPoster = ApiClient.IMAGE_PATH + movie.getBackdropPath();

                        GlideApp.with(DetailMovieActivity.this)
                                .load(Uri.parse(pathPoster))
                                .placeholder(R.drawable.placeholder)
                                .into(imPoster);

                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {

                }
            });

        }
    }
}
