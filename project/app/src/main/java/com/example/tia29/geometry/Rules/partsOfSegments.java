package com.example.tia29.geometry.Rules;

import android.content.Context;
import android.util.Log;

import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.Given;
import com.example.tia29.geometry.Entites.Item;
import com.example.tia29.geometry.Entites.Segment;
import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.Utils;

import java.util.ArrayList;
/*

*/


public class partsOfSegments implements MyRules {
    int val;
    boolean result=false;
    ArrayList<String> way = new ArrayList<String>();
    Exercise exercise;
    Context context;

    @Override
    public boolean check(Exercise exercise, Item[] items) {
int sum=0;
        ArrayList<Segment> ss = exercise.getSegments();
        ArrayList<String> tempWay = new ArrayList<String>();
        Segment segment = null;
        boolean b = false;
        boolean full = true;
		//check itemes if this kind segment
        if (items.length > 0 && items[0] instanceof Segment) {
            segment = (Segment) items[0];
            val = 0;


            for (Segment s : ss) {
                b = false;
                if (!s.equals(segment)) {
                    if (exercise.isPointsOnTheSegmentExact(segment, s.getPoint1()) && exercise.isPointsOnTheSegmentExact(segment, s.getPoint2())) {
                        b = true;
                    }
                    if (b && s.getValue() > 0) {
                        Utils.addAllWay(tempWay, exercise.getWayOfGiven1(new Given(s, EAll.שווה, s.getValue())));
                        val += s.getValue();
                        sum++;

                    }
                    else {
                        if (b) {
                            full = false;
                            return full;

                        }
                    }
                }
            }
            if (full &&sum>0) {
                Utils.addAllWay(way, tempWay);
                result=result(exercise, new Item[]{segment});
            }
        }
        return result;
    }


    @Override
    public boolean result(Exercise exercise, Item[] items) {
        boolean b = false;
        if (items[0] instanceof Segment) {
            Segment s = (Segment) items[0];
           if(exercise.setGivenValueOfSegment1(s,val,way)==true){
            b=true;}
        }
        return b;
    }

    @Override
    public boolean goOver(Exercise exercise, Context context) {
        boolean wasChanged = false;
        ArrayList<Segment> ss = exercise.getSegments();
        for (Segment s : ss) {
            if (check(exercise, new Item[]{s}))
                wasChanged = true;
        }
        return wasChanged;
    }
}
