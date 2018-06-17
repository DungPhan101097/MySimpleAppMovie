package com.example.lap10715.mysimpleappmovie.controllers.loaders;

import com.example.lap10715.mysimpleappmovie.controllers.ApiClient;
import com.example.lap10715.mysimpleappmovie.controllers.ApiHTTPRequest;
import com.example.lap10715.mysimpleappmovie.models.MoviesResponse;

import retrofit2.Call;

public class TopRatedLoader implements MovieLoader {
    @Override
    public Call<MoviesResponse> loadMovies(ApiHTTPRequest apiHTTPRequest, int page) {
        return apiHTTPRequest.getTopRatedMovies(ApiClient.API_KEY);
    }
}
