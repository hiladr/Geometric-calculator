package com.example.tia29.geometry.Rules;

import android.content.Context;

import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.Item;

import java.util.ArrayList;

/**
 * Created by Naomi on 26/02/2018.
 */
public interface MyRules {

     /*
     * This function goes over items and checks if they
     * fit the rule's basic conditions
     * e.g. for Pitagoras check if is a triangle
     * @param exercise
     * @param items
     * @returns true if the rule was learned
     */
    
    public boolean check(Exercise exercise, Item[] items);

    /*
     * This function checks if the rule can be learned.
     * if so, it checks if there is a better known way
     * and if not, sets a new given.
     * @param exercise
     * @param items
     * @returns true if the rule was learned
     */

    public boolean result(Exercise exercise,Item[] items);
    
    /*
     * This function goes over all objects in exercise
     * related to the rule and sends to be checked
     * @param exercise
     * @param context
     * @returns true if the rule was learned
     */
    public boolean goOver(Exercise exercise,Context context);
}


