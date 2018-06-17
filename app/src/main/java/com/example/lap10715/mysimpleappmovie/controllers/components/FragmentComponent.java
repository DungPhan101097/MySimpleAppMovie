package com.example.lap10715.mysimpleappmovie.controllers.components;

import android.support.v4.app.Fragment;

import com.example.lap10715.mysimpleappmovie.controllers.modules.FragmentModule;
import com.example.lap10715.mysimpleappmovie.uis.fragments.NowPlayingFragment;
import com.example.lap10715.mysimpleappmovie.uis.fragments.PopularFragment;
import com.example.lap10715.mysimpleappmovie.uis.fragments.TopRatedFragment;
import com.example.lap10715.mysimpleappmovie.uis.fragments.UpComingFragment;

import dagger.Component;

@Component(modules = {FragmentModule.class})
public interface FragmentComponent {
    void inject(NowPlayingFragment fragment);
    void inject(PopularFragment fragment);
    void inject(TopRatedFragment fragment);
    void inject(UpComingFragment fragment);


}
