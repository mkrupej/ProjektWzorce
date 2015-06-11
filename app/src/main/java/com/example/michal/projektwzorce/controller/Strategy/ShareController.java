package com.example.michal.projektwzorce.controller.Strategy;

/**
 * Created by Michal on 2015-06-11.
 */
public class ShareController{
    public void setStrategy(ShareStrategy strategy) {
        this.strategy = strategy;
    }

    public String setPortal(){
        return strategy.typeOfNetworks();
    }

    private ShareStrategy strategy;


}