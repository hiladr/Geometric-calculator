package com.example.tia29.geometry.Rules;

import android.content.Context;

import com.example.tia29.geometry.Entites.Angle;
import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.Given;
import com.example.tia29.geometry.Entites.Item;
import com.example.tia29.geometry.Entites.Segment;
import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.Utils;

import java.util.ArrayList;
/*
check if have value in one segment/angle 
and second segment/angle no have value,
if yes update first value like the second value. 
*/


public class SetValue implements MyRules {
    ArrayList<String> way = new ArrayList<String>();
    Context context;

    @Override
    public boolean check(Exercise exercise, Item[] items) {
        boolean b;
        if (items[0] instanceof Segment) {
            Segment s1 = (Segment) items[0];
            Segment s2 = (Segment) items[1];
			
            //if have value in one segment and second segment no have value.
            if (s1.getValue() != -1) {
                Utils.addAllWay(way, exercise.getWayOfGiven1(new Given(s1, EAll.שווה, s1.getValue())));

                if (way.size() > 0) {
                    return (result(exercise, new Item[]{s2, s1}));
                }
            } else {
                if (s2.getValue() != -1) {

                    Utils.addAllWay(way, exercise.getWayOfGiven1(new Given(s2, EAll.שווה, s2.getValue())));
                    if (way != null) {
                        return (result(exercise, new Item[]{s1, s2}));
                    }
                }
            }
        }
	 //if have value in one angle and second angle no have value.
        if (items[0] instanceof Angle) {
            Angle a1 = (Angle) items[0];
            Angle a2 = (Angle) items[1];
            if (a1.getValue() != -1) {
                Utils.addAllWay(way, exercise.getWayOfGiven1(new Given(a1, EAll.שווה, a1.getValue())));
                if (way != null) {
                    return result(exercise, new Item[]{a2, a1});
                }

            } else {
                if (a2.getValue() != -1) {
                    Utils.addAllWay(way, exercise.getWayOfGiven1(new Given(a2, EAll.שווה, a2.getValue())));
                    if (way != null) {
                        return result(exercise, new Item[]{a1, a2});
                    }
                }

            }
        }
        return false;
    }

    @Override
    public boolean result(Exercise exercise, Item[] items) {
        //update first value like the second value
        boolean wasChanged = false;
        if (items[0] instanceof Segment) {
            Segment s1 = (Segment) items[0];
            Segment s2 = (Segment) items[1];
            if (exercise.setGivenValueOfSegment1(s1, s2.getValue(), way))
                wasChanged = true;
        }
        if (items[0] instanceof Angle) {
            Angle a1 = (Angle) items[0];
            Angle a2 = (Angle) items[1];
            if (exercise.setGivenValueOfAngle1(a1, a2.getValue(), way))
                wasChanged = true;
        }
        return wasChanged;
    }

    @Override
    public boolean goOver(Exercise exercise, Context context) {

        this.context = context;
        boolean bool = false;
        Given tempGiv = null;
        String wayEqual = "";
        Given given;
        int size = exercise.getGivenList().size();
        for (int i = 0; i < size; i++) {
            way = new ArrayList<String>();
            given = exercise.getGivenList().get(i);
            if (given.getOperand() == EAll.שווה && given.getItem1() != null && given.getItem2() != null) {
                //if two segments equale
                if (given.getItem1() instanceof Segment) {
                    Segment s1 = exercise.getSegmentById(given.getItem1().getId());
                    Segment s2 = exercise.getSegmentById(given.getItem2().getId());
                    Utils.addAllWay(way, given.getWay1());
                    if (check(exercise, new Item[]{s1, s2})) {
                        bool = true;
                    }
                }
                //if this angle
                if (given.getItem1() instanceof Angle) {
                    Angle a1 = exercise.getAngleById(given.getItem1().getId());
                    Angle a2 = exercise.getAngleById(given.getItem2().getId());
                    Utils.addAllWay(way, given.getWay1());
                    if (check(exercise, new Item[]{a1, a2})) {
                        bool = true;
                    }


                }
            }
        }

        return bool;
    }
}
