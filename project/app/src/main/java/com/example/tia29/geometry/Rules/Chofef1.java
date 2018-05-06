package com.example.tia29.geometry.Rules;

import android.content.Context;

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


public class Chofef1 implements MyRules {
    Exercise exercise;
    Context context;
    private boolean wasChanged = false;
    private ArrayList<String> way=new ArrayList<String>();
    @Override
    public boolean check(Exercise exercise, Item[] items) {
        if (items.length>0 && items[0] instanceof Triangle && items[1] instanceof Triangle) {
            Triangle t1 = (Triangle) items[0];
            Triangle t2 = (Triangle) items[1];
            Segment ss1[] = t1.getSegments();
            Segment ss2[] = t2.getSegments();
            ArrayList<String> w1, w2, w3;

            w1 = exercise.getWayOfGivenWithEq(new Given(ss1[0], EAll.שווה, ss2[0]));
            if (w1 != null && w1.size() > 0) {
                w2 = exercise.getWayOfGivenWithEq(new Given(ss1[1], EAll.שווה, ss2[1]));
                w3 = exercise.getWayOfGivenWithEq(new Given(ss1[2], EAll.שווה, ss2[2]));
                if (w2 != null && w2.size() > 0 && w3 != null && w3.size() > 0) {
                    way = new ArrayList<String>();
                    Utils.addAllWay(way, w1);
                    Utils.addAllWay(way, w2);
                    Utils.addAllWay(way, w3);
                    wasChanged = result(exercise, new Item[]{t1, t2, ss1[0], ss1[1], ss1[2], ss2[0], ss2[1], ss2[2]});
                } else
                w2 = exercise.getWayOfGivenWithEq(new Given(ss1[1], EAll.שווה, ss2[2]));
                w3 = exercise.getWayOfGivenWithEq(new Given(ss1[2], EAll.שווה, ss2[1]));
                if (w2 != null && w2.size() > 0 && w3 != null && w3.size() > 0) {
                    way = new ArrayList<String>();
                    Utils.addAllWay(way, w1);
                    Utils.addAllWay(way, w2);
                    Utils.addAllWay(way, w3);
                    wasChanged = result(exercise, new Item[]{t1, t2, ss1[0], ss1[1], ss1[2], ss2[0], ss2[2], ss2[1]});
                }
            }


            w1 = exercise.getWayOfGivenWithEq(new Given(ss1[0], EAll.שווה, ss2[1]));
            if (w1 != null && w1.size() > 0) {
                w2 = exercise.getWayOfGivenWithEq(new Given(ss1[1], EAll.שווה, ss2[0]));
                w3 = exercise.getWayOfGivenWithEq(new Given(ss1[2], EAll.שווה, ss2[2]));
                if (w2 != null && w2.size() > 0 && w3 != null && w3.size() > 0) {
                    way = new ArrayList<String>();
                    Utils.addAllWay(way, w1);
                    Utils.addAllWay(way, w2);
                    Utils.addAllWay(way, w3);
                    wasChanged = result(exercise, new Item[]{t1, t2, ss1[0], ss1[1], ss1[2], ss2[1], ss2[0], ss2[2]});
                } else {
                    w2 = exercise.getWayOfGivenWithEq(new Given(ss1[1], EAll.שווה, ss2[2]));
                    w3 = exercise.getWayOfGivenWithEq(new Given(ss1[2], EAll.שווה, ss2[0]));
                    if (w2 != null && w2.size() > 0 && w3 != null && w3.size() > 0) {
                        way = new ArrayList<String>();
                        Utils.addAllWay(way, w1);
                        Utils.addAllWay(way, w2);
                        Utils.addAllWay(way, w3);
                        wasChanged = result(exercise, new Item[]{t1, t2, ss1[0], ss1[1], ss1[2], ss2[1], ss2[2], ss2[0]});
                    }
                }
            }
            w1 = exercise.getWayOfGivenWithEq(new Given(ss1[0], EAll.שווה, ss2[2]));
            if (w1 != null && w1.size() > 0) {
                w2 = exercise.getWayOfGivenWithEq(new Given(ss1[1], EAll.שווה, ss2[0]));
                w3 = exercise.getWayOfGivenWithEq(new Given(ss1[2], EAll.שווה, ss2[1]));
                if (w2 != null && w2.size() > 0 && w3 != null && w3.size() > 0) {
                    way = new ArrayList<String>();
                    Utils.addAllWay(way, w1);
                    Utils.addAllWay(way, w2);
                    Utils.addAllWay(way, w3);
                    wasChanged = result(exercise, new Item[]{t1, t2, ss1[0], ss1[1], ss1[2], ss2[2], ss2[0], ss2[1]});
                } else {
                    w2 = exercise.getWayOfGivenWithEq(new Given(ss1[1], EAll.שווה, ss2[1]));
                    w3 = exercise.getWayOfGivenWithEq(new Given(ss1[2], EAll.שווה, ss2[0]));
                    if (w2 != null && w2.size() > 0 && w3 != null && w3.size() > 0) {
                        way = new ArrayList<String>();
                        Utils.addAllWay(way, w1);
                        Utils.addAllWay(way, w2);
                        Utils.addAllWay(way, w3);
                        wasChanged = result(exercise, new Item[]{t1, t2, ss1[0], ss1[1], ss1[2], ss2[2], ss2[1], ss2[0]});
                    }
                }
            }
        }
        return wasChanged;
    }
    @Override
    public boolean result(Exercise exercise, Item[] items) {
        String sentence=context.getString(R.string.chafifa1);
        boolean wasChanged = false;
        if (items[0] instanceof Triangle && items[1] instanceof Triangle) {
            Triangle t1 = (Triangle) items[0];
            Triangle t2 = (Triangle) items[1];
            Given temp = new Given(t1, EAll.חופף, t2);
            way.add(sentence);
            Given g = exercise.addGeneralGiven1(temp, way);
            if (g.equals(temp)) {
                wasChanged = true;
            }
            Segment[] ss1 = new Segment[3];
            Segment[] ss2 = new Segment[3];
            ss1[0] = (Segment) items[2];
            ss1[1] = (Segment) items[3];
            ss1[2] = (Segment) items[4];
            ss2[0] = (Segment) items[5];
            ss2[1] = (Segment) items[6];
            ss2[2] = (Segment) items[7];
            Angle a1;
            Angle a2;
            Given giv;
            a1 = exercise.getAngleForSegemntsInTriangle(t1, ss1[0], ss1[1]);
            a2 =  exercise.getAngleForSegemntsInTriangle( t2, ss2[0], ss2[1]);
            giv = new Given(a1, EAll.שווה, a2);
           way=new ArrayList<String>();
            Utils.addAllWay(way,g.getWay1());
            way.add(context.getString(R.string.chambachash));
            exercise.addGeneralGiven1(giv, way);

            a1 =  exercise.getAngleForSegemntsInTriangle( t1, ss1[1], ss1[2]);
            a2 =  exercise.getAngleForSegemntsInTriangle( t2, ss2[1], ss2[2]);
            giv = new Given(a1, EAll.שווה, a2);
            way=new ArrayList<String>();
            Utils.addAllWay(way,g.getWay1());
            way.add(context.getString(R.string.chambachash));

            exercise.addGeneralGiven1(giv,way);

            a1 =  exercise.getAngleForSegemntsInTriangle( t1, ss1[2], ss1[0]);
            a2 =  exercise.getAngleForSegemntsInTriangle(t2, ss2[2], ss2[0]);
            giv = new Given(a1, EAll.שווה, a2);
           way=new ArrayList<String>();
            Utils.addAllWay(way,g.getWay1());
            way.add(context.getString(R.string.chambachash));
            exercise.addGeneralGiven1(giv,way);
        }
        return wasChanged;
    }
    @Override
    public boolean goOver(Exercise exercise,Context context) {
        this.context=context;
        this.exercise=exercise;
        boolean b = false;
        Triangle t1,t2;
        ArrayList<Triangle> triangles = exercise.getTriangles();
        int size=triangles.size();
        for (int i=0;i<size;i++) {
            t1=triangles.get(i);
            for (int j=i;j<size;j++) {
                t2=triangles.get(j);
                if(!t1.equals(t2)){
                if (check(exercise, new Item[]{t1, t2})) b = true;}
            }
        }
        return b;
    }

}
