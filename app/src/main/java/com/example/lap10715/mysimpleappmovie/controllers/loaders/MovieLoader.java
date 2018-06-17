package com.example.lap10715.mysimpleappmovie.controllers.loaders;

import com.example.lap10715.mysimpleappmovie.controllers.ApiHTTPRequest;
import com.example.lap10715.mysimpleappmovie.models.MoviesResponse;

import retrofit2.Call;

public interface MovieLoader {
    Call<MoviesResponse> loadMovies(ApiHTTPRequest apiHTTPRequest, int page);
}
