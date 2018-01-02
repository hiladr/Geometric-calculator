package com.example.tia29.geometry;

import android.content.Context;

import java.util.ArrayList;


public class Utils {

    //add a way to to a way
    public static void addAllWay(ArrayList<String> a,ArrayList <String>b){
        if(b!=null&&a!=null)
            a.addAll(b);
    }
    //gets a length of a way of a given
    public static  int lengthOfWay(ArrayList<String> way) {
        int count =0;
        for(int i=0;i<way.size();i++)
        {
            count+=way.get(i).length();

        }
        return  count;
    }


}
