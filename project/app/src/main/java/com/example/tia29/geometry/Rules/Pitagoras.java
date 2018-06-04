package com.example.tia29.geometry.Rules;

import android.content.Context;
import android.provider.Telephony;

import com.example.tia29.geometry.Entites.Angle;
import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.Given;
import com.example.tia29.geometry.Entites.Item;
import com.example.tia29.geometry.Entites.Segment;
import com.example.tia29.geometry.Entites.Triangle;
import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.Utils;

import java.util.ArrayList;


public class Pitagoras implements MyRules {
    ArrayList<String> way = new ArrayList<String>();
    Exercise exercise;
    Context context;

    @Override
    public boolean check(Exercise exercise, Item[] items) {
        boolean bool = false;
        if (items[0] instanceof Triangle) {
            Triangle t = (Triangle) items[0];
            Given g = exercise.getGivenInExercise(new Given(t, EAll.ישר_זוית));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                //     way=);
                if (result(exercise, new Item[]{t, g.getItem2()}))
                    bool = true;
            }
        }
        return bool;
    }

    @Override
    public boolean result(Exercise exercise, Item[] items) {
        boolean bool = false;
        ArrayList<String> waygiv1;
        ArrayList<String> waygiv2;
        if (items[0] instanceof Triangle && items[1] instanceof Angle) {
            String sentence = context.getString(R.string.pitagoras);
            Triangle triangle = (Triangle) items[0];
            Angle angle = (Angle) items[1];
            Segment s1 = exercise.GetSegmentByName("" + angle.GetName().charAt(0) + angle.GetName().charAt(1) + "");
            Segment s2 = exercise.GetSegmentByName("" + angle.GetName().charAt(2) + angle.GetName().charAt(1) + "");
            Segment s3 = exercise.GetSegmentByName("" + angle.GetName().charAt(0) + angle.GetName().charAt(2) + "");
            if (s1.getValue() > 0 && s2.getValue() > 0 && s3.getValue() <= 0) {
                waygiv1 = exercise.getWayOfGiven1(new Given(s1, EAll.שווה, s1.getValue()));
                waygiv2 = exercise.getWayOfGiven1(new Given(s2, EAll.שווה, s2.getValue()));
                if (waygiv1 != null && waygiv1.size() > 0 && waygiv2 != null && waygiv2.size() > 0) {
                    Utils.addAllWay(way, waygiv1);
                    Utils.addAllWay(way, waygiv2);
                    way.add(sentence);
                    if (exercise.setGivenValueOfSegment1(s3, Math.sqrt(s1.getValue() * s1.getValue() + s2.getValue() * s2.getValue()), way))

                        bool = true;
                }
            }
            if (s1.getValue() > 0 && s2.getValue() <= 0 && s3.getValue() > 0) {
                waygiv1 = exercise.getWayOfGiven1(new Given(s1, EAll.שווה, s1.getValue()));
                waygiv2 = exercise.getWayOfGiven1(new Given(s3, EAll.שווה, s3.getValue()));
                if (waygiv1 != null && waygiv1.size() > 0 && waygiv2 != null && waygiv2.size() > 0) {
                    Utils.addAllWay(way, waygiv1);
                    Utils.addAllWay(way, waygiv2);
                    way.add(sentence);
                    //way = waygiv1 + "\n" + waygiv2;
                    if (exercise.setGivenValueOfSegment1(s2, Math.sqrt(s3.getValue() * s3.getValue() - s1.getValue() * s1.getValue()), way))

                        bool = true;
                }
            }
            if (s1.getValue() <= 0 && s2.getValue() > 0 && s3.getValue() > 0) {
                waygiv1 = exercise.getWayOfGiven1(new Given(s2, EAll.שווה, s2.getValue()));
                waygiv2 = exercise.getWayOfGiven1(new Given(s3, EAll.שווה, s3.getValue()));
                if (waygiv1 != null && waygiv1.size() > 0 && waygiv2 != null && waygiv2.size() > 0) {
                    Utils.addAllWay(way, waygiv1);
                    Utils.addAllWay(way, waygiv2);
                    way.add(sentence);
                    if (exercise.setGivenValueOfSegment1(s1, Math.sqrt(s3.getValue() * s3.getValue() - s2.getValue() * s2.getValue()), way))

                        bool = true;
                }
            }
        }


        return bool;
    }

    @Override
    public boolean goOver(Exercise exercise, Context context) {
        this.context = context;
        ArrayList<Triangle> triangles = exercise.getTriangles();
        boolean b = false;
        for (Triangle triangle : triangles) {
            boolean temp = check(exercise, new Item[]{triangle});
            if (temp) {
                b = true;
            }
        }
        return b;
    }

}

