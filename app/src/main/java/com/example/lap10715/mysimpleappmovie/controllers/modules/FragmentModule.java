package com.example.lap10715.mysimpleappmovie.controllers.modules;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.lap10715.mysimpleappmovie.controllers.ApiHTTPRequest;
import com.example.lap10715.mysimpleappmovie.controllers.loaders.MovieLoader;
import com.example.lap10715.mysimpleappmovie.controllers.loaders.NowPlayingLoader;
import com.example.lap10715.mysimpleappmovie.controllers.loaders.PopularLoader;
import com.example.lap10715.mysimpleappmovie.controllers.loaders.TopRatedLoader;
import com.example.lap10715.mysimpleappmovie.controllers.loaders.UpComingLoader;
import com.example.lap10715.mysimpleappmovie.models.Movie;
import com.example.lap10715.mysimpleappmovie.uis.adapters.MyAdapter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class FragmentModule {
    private Context context;
    private RecyclerView recyclerView;

    public FragmentModule(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Provides
    public MyAdapter provideAdapter(){
        return new MyAdapter(context, recyclerView);
    }

    @Provides
    @Named(value = "now_playing")
    public MovieLoader provideNowPlayingLoader(){
        return new NowPlayingLoader();
    }

    @Provides
    @Named(value = "top_rated")
    public MovieLoader provideTopRatedLoader(){
        return new TopRatedLoader();
    }

    @Provides
    @Named(value = "popular")
    public MovieLoader providePopularLoader(){
        return new PopularLoader();
    }

    @Provides
    @Named(value = "up_coming")
    public MovieLoader provideUpComingLoader(){
        return new UpComingLoader();
    }


}
