package com.example.michal.projektwzorce.controller.Strategy;

import android.content.Intent;

/**
 * Created by Krzysztof on 2015-06-10.
 */
public class MailStrategy extends ShareContext implements ShareStrategy
{
    public String typeOfNetworks (){
        String typeOfNetwork = "mail";
        return typeOfNetwork;
    };

}
