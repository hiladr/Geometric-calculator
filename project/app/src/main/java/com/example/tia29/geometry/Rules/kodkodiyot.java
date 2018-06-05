package com.example.tia29.geometry.Rules;

import android.content.Context;

import com.example.tia29.geometry.Entites.*;
import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.R;

import java.util.ArrayList;
/*
find kodkodiyot angles 
*/
public class kodkodiyot implements MyRules {
    Given given;
    Exercise exercise;
    Context context;
    ArrayList<String> way = new ArrayList<String>();

    @Override
	// sent exercise and itemes to check if this kind angle
    public boolean check(Exercise exercise, Item[] items) {
        if (items.length > 1 && items[0] instanceof Angle && items[1] instanceof Angle) {
            Angle a1 = (Angle) items[0];
            Angle a2 = (Angle) items[1];
            MyPoint p1 = a1.getPoints()[0];
            MyPoint p2 = a1.getPoints()[1];
            MyPoint p3 = a1.getPoints()[2];
            MyPoint q1 = a2.getPoints()[0];
            MyPoint q2 = a2.getPoints()[1];
            MyPoint q3 = a2.getPoints()[2];

            Segment s1 = null, s2 = null;
			
			//check if angles a1, a2 kodkodiyot
            if (!a1.equals(a2) && p2.equals(q2)) {
                if (exercise.doesLineExist(p3, q3) && (exercise.doesLineExist(p1, q1))) {
                    s1 = exercise.GetSegmentByName(p3.GetName() + q3.GetName());
                    s2 = exercise.GetSegmentByName(p1.GetName() + q1.GetName());
                }

                if (exercise.doesLineExist(p3, q1) && (exercise.doesLineExist(p1, q3))) {
                    s1 = exercise.GetSegmentByName(p3.GetName() + q1.GetName());
                    s2 = exercise.GetSegmentByName(p1.GetName() + q3.GetName());
                }
                if (s1 != null && s2 != null && exercise.isPointsOnTheSegment(s1, p2) && exercise.isPointsOnTheSegment(s2, p2)) {

                    return result(exercise, new Item[]{a1, a2});
                }
            }

        }
        return false;
    }

    @Override
	//add sentence to way
    public boolean result(Exercise exercise, Item[] items) {
        boolean wasChanghed = false;
        Angle a1 = (Angle) items[0];
        Angle a2 = (Angle) items[1];
        ArrayList<String> tempWay = new ArrayList<String>();
        tempWay.add(context.getString(R.string.kodkodiyot));
        wasChanghed = exercise.setGivenThatAnglesEqual1(a1, a2, tempWay);
        return wasChanghed;
    }

    @Override
    public boolean goOver(Exercise exercise, Context context) {
        this.context = context;
        boolean b = false;
        Angle a1, a2;
        ArrayList<Angle> angles = exercise.getAngles();
        int size = angles.size();
        for (int i = 0; i < size; i++) {
            a1 = angles.get(i);
            for (int j = i; j < size; j++) {
                a2 = angles.get(j);
                if (!a1.equals(a2)) {
                    boolean temp = check(exercise, new Item[]{a1, a2});
                    if (temp) b = true;
                }
            }

        }
        return b;
    }
}
