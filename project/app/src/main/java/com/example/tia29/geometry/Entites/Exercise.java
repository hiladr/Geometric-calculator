package com.example.tia29.geometry.Entites;

import android.content.Context;
import android.renderscript.Sampler;
import android.util.Log;

import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.UI.MainActivity;
import com.example.tia29.geometry.Utils;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Naomi on 14/12/2017.
 */
public class Exercise {

    private MainActivity mainActivity;
    private ArrayList<MyPoint> points;
    private Context context;
    private int[] letters;//free letters for new elements

    public Exercise(Context context) {
        points = new ArrayList<MyPoint>();
        letters = new int[26];
        for (int i = 0; i < 26; i++) {
            letters[i] = 0;
        }
        this.context = context;
    }
	
	 public void freeLetter(char c) {

        if ((c - 'a') >= 0 && (c - 'a') < 26)
            letters[c - 'a'] = 0;
    }//free a not needed letter
	
    public ArrayList<MyPoint> getPoints() {
        ArrayList<MyPoint> p = new ArrayList<MyPoint>();

        for (MyPoint key : points) {
            p.add(key);

        }
        return p;
    }


}
