package com.example.michal.projektwzorce.model;

/**
 * Created by Michal on 2015-05-18.
 */
public class Photography {
    private static Photography ourInstance = new Photography();

    public static Photography getInstance() {
        return ourInstance;
    }

    private Photography() {


    }
}
