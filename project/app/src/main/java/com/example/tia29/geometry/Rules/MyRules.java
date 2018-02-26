package com.example.tia29.geometry.Rules;

import android.content.Context;

import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.Item;

import java.util.ArrayList;

/**
 * Created by Naomi on 26/02/2018.
 */
public interface MyRules {


    public boolean check(Exercise exercise, Item[] items);


    public boolean result(Exercise exercise,Item[] items);


    public boolean goOver(Exercise exercise,Context context);
}


