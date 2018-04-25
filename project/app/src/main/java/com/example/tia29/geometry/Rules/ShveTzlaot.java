package com.example.tia29.geometry.Rules;

import android.content.Context;

import com.example.tia29.geometry.Entites.*;
import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.Utils;

import java.util.ArrayList;


public class ShveTzlaot implements MyRules {
    ArrayList<String> way = new ArrayList<String>();
    Context context;

    @Override
    public boolean check(Exercise exercise, Item[] items) {
        boolean b = false;
        Triangle t;
        if (items[0] instanceof Triangle) {
            t = (Triangle) items[0];
            Segment[] segments = t.getSegments();
            ArrayList<String> way1, way2, way3, tempWay = null;
            way = new ArrayList<String>();
            Utils.addAllWay(way, exercise.getWayOfGiven1(new Given(t, EAll.שווה_צלעות)));
            if (way.size() > 0) {
                return result(exercise, new Item[]{t});
            }
            way1 = new ArrayList<String>();
            Utils.addAllWay(way1, exercise.getWayOfGiven1(new Given(segments[0], EAll.שווה, segments[1])));
            way2 = new ArrayList<String>();
            Utils.addAllWay(way2, exercise.getWayOfGiven1(new Given(segments[1], EAll.שווה, segments[2])));
            way3 = new ArrayList<String>();
            Utils.addAllWay(way3, exercise.getWayOfGiven1(new Given(segments[0], EAll.שווה, segments[2])));
            if (way1.size() > 0 && way2.size() > 0 && way3.size() > 0) {
                tempWay = new ArrayList<String>();
                Utils.addAllWay(tempWay, way1);
                Utils.addAllWay(tempWay, way2);
                Utils.addAllWay(tempWay, way3);
            }
            if (tempWay != null && tempWay.size() > 0) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, tempWay);
                b = result(exercise, new Item[]{t});


            }


        }


        return b;
    }

    @Override
    public boolean result(Exercise exercise, Item[] items) {
        boolean bool = false;
        String sentence1 = context.getString(R.string.shveTzlaotTlaot);
        String sentence2 = context.getString(R.string.shveTzlaotZaviot);
        String sentence3 = context.getString(R.string.shveTzlaot60);
       String sentence4 = context.getString(R.string.shveTzlaotTzlaot);
        Triangle t = (Triangle) items[0];
        Segment s1 = t.getSegments()[0];
        Segment s2 = t.getSegments()[1];
        Segment s3 = t.getSegments()[2];
        Angle a1 = t.getAngles()[0];
        Angle a2 = t.getAngles()[1];
        Angle a3 = t.getAngles()[2];

        way.add(sentence4);
        Given g = new Given(t, EAll.שווה_צלעות);
        Given g1 = exercise.addGeneralGiven1(g, way);
        if (g.equals(g1)) {
            bool = true;
        }
        way = new ArrayList<String>();
        Utils.addAllWay(way, g1.getWay1());
        way.add(sentence1);
        if (exercise.setGivenThatSegmentsEqual1(s1, s2, way)) {
            bool = true;
        }
        if (exercise.setGivenThatSegmentsEqual1(s1, s3, way)) {
            bool = true;
        }
        if (exercise.setGivenThatSegmentsEqual1(s2, s3, way)) {
            bool = true;
        }
        way.remove(way.size() - 1);
        way.add(sentence2);
        if (exercise.setGivenThatAnglesEqual1(a1, a2, way)) {
            bool = true;
        }
        if (exercise.setGivenThatAnglesEqual1(a1, a3, way)) {
            bool = true;
        }
        if (exercise.setGivenThatAnglesEqual1(a2, a3, way)) {
            bool = true;
        }
        way.remove(way.size() - 1);
        way.add(sentence3);
        if (exercise.setGivenValueOfAngle1(a1, 60, way)) {
            bool = true;
        }
        if (exercise.setGivenValueOfAngle1(a2, 60, way)) {
            bool = true;
        }
        if (exercise.setGivenValueOfAngle1(a3, 60, way)) {
            bool = true;
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
