package com.example.lap10715.mysimpleappmovie.controllers.components;

import com.example.lap10715.mysimpleappmovie.controllers.modules.AdapterModule;
import com.example.lap10715.mysimpleappmovie.uis.DetailMovieActivity;
import com.example.lap10715.mysimpleappmovie.uis.adapters.MyAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AdapterModule.class})
public interface AdapterComponent {

    void inject(MyAdapter myAdapter);

    void inject(DetailMovieActivity detailMovieActivity);
}
