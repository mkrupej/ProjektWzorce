package com.example.michal.projektwzorce.controller.Strategy;

/**
 * Created by Krzysztof on 2015-06-10.
 */
public class ShareContext
{
    ShareStrategy shareStrategy;

    private String nazwa;

    public void setShareStrategy(ShareStrategy shareStrategy) {
        this.shareStrategy = shareStrategy;
    }

    public ShareContext()
    {
        this.nazwa = new String();
    }
}
