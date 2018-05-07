package com.example.tia29.geometry.Rules;

import android.content.Context;

import com.example.tia29.geometry.Entites.Angle;
import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.Given;
import com.example.tia29.geometry.Entites.Item;
import com.example.tia29.geometry.Entites.MyPoint;
import com.example.tia29.geometry.Entites.Segment;
import com.example.tia29.geometry.Entites.Triangle;
import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.Utils;

import java.util.ArrayList;


public class MeshulahHazahav implements MyRules {
    Exercise exercise;
    Context context;
    ArrayList<String> way = new ArrayList<String>();

    @Override
    public boolean check(Exercise exercise, Item[] items) {
        boolean b = false;
        if (items[0] instanceof Triangle) {
            Triangle t1 = (Triangle) items[0];

            Angle angles[] = t1.getAngles();
            Given g = exercise.getGivenInExercise(new Given(t1, EAll.ישר_זוית));
            if (g != null) {
                Angle a = (Angle) g.getItem2();
                if (angles[0].getValue() == 90 && angles[1].getValue() == 60) {
                    ArrayList<String> way1 = exercise.getWayOfGiven1(new Given(angles[1], EAll.שווה, 60));
                    ArrayList<String> way2 = exercise.getWayOfGiven1(new Given(angles[2], EAll.שווה, 30));
                    way = new ArrayList<String>();
                    Utils.addAllWay(way, way1);
                    Utils.addAllWay(way, way2);
                    b = result(exercise, new Item[]{t1, angles[2], angles[1], angles[0]});
                }
                if (angles[0].getValue() == 90 && angles[1].getValue() == 30) {
                    ArrayList<String> way1 = exercise.getWayOfGiven1(new Given(angles[2], EAll.שווה, 60));
                    ArrayList<String> way2 = exercise.getWayOfGiven1(new Given(angles[1], EAll.שווה, 30));
                    way = new ArrayList<String>();
                    Utils.addAllWay(way, way1);
                    Utils.addAllWay(way, way2);
                    b = result(exercise, new Item[]{t1, angles[1], angles[2], angles[0]});
                }
                if (angles[0].getValue() == 30 && angles[1].getValue() == 60) {
                    ArrayList<String> way1 = exercise.getWayOfGiven1(new Given(angles[1], EAll.שווה, 60));
                    ArrayList<String> way2 = exercise.getWayOfGiven1(new Given(angles[0], EAll.שווה, 30));
                    way = new ArrayList<String>();
                    Utils.addAllWay(way, way1);
                    Utils.addAllWay(way, way2);
                    b = result(exercise, new Item[]{t1, angles[0], angles[1], angles[2]});
                }
                if (angles[0].getValue() == 30 && angles[1].getValue() == 90) {
                    ArrayList<String> way1 = exercise.getWayOfGiven1(new Given(angles[2], EAll.שווה, 60));
                    ArrayList<String> way2 = exercise.getWayOfGiven1(new Given(angles[0], EAll.שווה, 30));
                    way = new ArrayList<String>();
                    Utils.addAllWay(way, way1);
                    Utils.addAllWay(way, way2);
                    b = result(exercise, new Item[]{t1, angles[0], angles[2], angles[1]});
                }
                if (angles[0].getValue() == 60 && angles[1].getValue() == 90) {
                    ArrayList<String> way1 = exercise.getWayOfGiven1(new Given(angles[0], EAll.שווה, 60));
                    ArrayList<String> way2 = exercise.getWayOfGiven1(new Given(angles[2], EAll.שווה, 30));
                    way = new ArrayList<String>();
                    Utils.addAllWay(way, way1);
                    Utils.addAllWay(way, way2);
                    //way=way1+way2;
                    b = result(exercise, new Item[]{t1, angles[2], angles[0], angles[1]});
                }
                if (angles[0].getValue() == 60 && angles[1].getValue() == 30) {
                    ArrayList<String> way1 = exercise.getWayOfGiven1(new Given(angles[0], EAll.שווה, 60));
                    ArrayList<String> way2 = exercise.getWayOfGiven1(new Given(angles[1], EAll.שווה, 30));
                    way = new ArrayList<String>();
                    Utils.addAllWay(way, way1);
                    Utils.addAllWay(way, way2);
                    //way=way1+way2;
                    b = result(exercise, new Item[]{t1, angles[1], angles[0], angles[2]});
                }
            }

        }
        return b;
    }

    @Override
    public boolean result(Exercise exercise, Item[] items) {
        boolean b = false;
        Triangle t = (Triangle) items[0];
        Given temp, g;
        Angle thirty = (Angle) items[1];
        Angle sixty = (Angle) items[2];
        Angle ninety = (Angle) items[3];
        String sentence = context.getString(R.string.meshulahHazahav);

        Segment yeter = exercise.getSegmentForAngleInTriangle(t, thirty, sixty);
        Segment nitzav = exercise.getSegmentForAngleInTriangle(t, ninety, sixty);
        if (yeter.getValue() > 0) {
            ArrayList<String> tempWay = exercise.getWayOfGiven1(new Given(yeter, EAll.שווה, yeter.getValue()));
            temp = new Given(nitzav, EAll.שווה, yeter.getValue() / 2);
            Utils.addAllWay(way, tempWay);
            way.add(sentence);
            g = exercise.addGeneralGiven1(temp, way);
            if (g.equals(temp)) {
                b = true;
            }
        }
        if (nitzav.getValue() > 0) {
            ArrayList<String> tempWay = exercise.getWayOfGiven1(new Given(nitzav, EAll.שווה, nitzav.getValue()));
            temp = new Given(yeter, EAll.שווה, nitzav.getValue() * 2);
            Utils.addAllWay(way, tempWay);
            way.add(sentence);
            g = exercise.addGeneralGiven1(temp, way);

            if (g.equals(temp)) {
                b = true;
            }
        }
        return b;
    }

    @Override
    public boolean goOver(Exercise exercise, Context context) {
        this.context = context;
        boolean b = false;
        ArrayList<Triangle> triangles = exercise.getTriangles();
        for (Triangle t1 : triangles) {

            if (check(exercise, new Item[]{t1}))
                b = true;
        }
        return b;
    }


}
