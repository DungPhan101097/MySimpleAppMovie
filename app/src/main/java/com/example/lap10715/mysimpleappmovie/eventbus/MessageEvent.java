package com.example.lap10715.mysimpleappmovie.eventbus;

import com.example.lap10715.mysimpleappmovie.uis.adapters.MyAdapter;

public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
