package com.example.tia29.geometry.Rules;

import android.content.Context;

import com.example.tia29.geometry.Entites.*;
import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.Utils;

import java.util.ArrayList;

import javax.xml.transform.Result;


public class ShveShokayim implements MyRules {

    ArrayList<String> way = new ArrayList<String>();
    Context context;

    @Override
    public boolean check(Exercise exercise, Item[] items) {

        boolean flag = false;
        if (items[0] instanceof Triangle) {
            Triangle t = (Triangle) items[0];
            Given given = exercise.getGivenInExercise(new Given(t, EAll.שווה_שוקיים));
            if (given != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, given.getWay1());
                return result(exercise, new Item[]{t, given.getItem2(), given.getItem3()});
            }
            Segment[] segments = t.getSegments();
            Segment s1 = segments[0];
            Segment s2 = segments[1];
            Segment s3 = segments[2];


            ArrayList<String> way1 = null, way2 = null;


            way1 = exercise.getWayOfGiven1(new Given(s1, EAll.שווה, s3));
            if (way1 != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, way1);
                if (result(exercise, new Item[]{t, s1, s3})) {
                    flag = true;
                }
            }
            //  way1 = null;
            way1 = new ArrayList<String>();
            Utils.addAllWay(way1, exercise.getWayOfGiven1(new Given(s1, EAll.שווה, s2)));
            if (way1 != null && way1.size() > 0) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, way1);
                if (result(exercise, new Item[]{t, s1, s2})) {
                    flag = true;
                }
            }


            way1 = new ArrayList<String>();
            Utils.addAllWay(way1, exercise.getWayOfGiven1(new Given(s2, EAll.שווה, s3)));
            //    way1 = exercise.getWayOfGiven1(new Given(s2, EAll.שווה, s3));
            if (way1 != null && way1.size() > 0) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, way1);
                if (result(exercise, new Item[]{t, s2, s3})) {
                    flag = true;
                }

            }
        }


        //}
        return flag;
    }


    @Override
    public boolean result(Exercise exercise, Item[] items) {
        Given tempGiven;
        boolean bool = false;
        Triangle t = (Triangle) items[0];
        if (items[1] instanceof Segment && items[2] instanceof Segment) {
            Segment s1 = (Segment) items[1];
            Segment s2 = (Segment) items[2];
            Angle[] angles = t.getAngles();

            char a = s1.GetName().charAt(0);
            char b = s1.GetName().charAt(1);
            char c = s2.GetName().charAt(0);
            char d = s2.GetName().charAt(1);
            String name;
            if (a == c) {
                name = "" + b + a + d;
            } else if (a == d) {
                name = "" + b + a + c;
            } else
                name = "" + c + b + d;


            Angle al = exercise.getAngleByName(name);
            int ind1 = 0, ind2 = 0;
            //בדיקה אלו זוויות שוות
            if (angles[0].equals(al)) {
                ind1 = 1;
                ind2 = 2;
            }
            if (angles[1].equals(al)) {
                ind1 = 0;
                ind2 = 2;
            }
            if (angles[2].equals(al)) {
                ind1 = 0;
                ind2 = 1;
            }
            Given tempGiven1 = new Given(s1, EAll.שווה, s2);
            Given g = exercise.addGeneralGiven1(tempGiven1, way);
            if (g.equals(tempGiven1))
                bool = true;

            g = new Given(t, EAll.שווה_שוקיים, s1, s2);
            way.add(context.getString(R.string.tzlaotshveShokayinm));
            Given g1 = exercise.addGeneralGiven1(g, way);
            if (g.equals(g1))
                bool = true;

            way = new ArrayList<String>();
            Utils.addAllWay(way, g1.getWay1());

            way.add(context.getString(R.string.shveShokimzaviotShavot));
            if (exercise.setGivenThatAnglesEqual1(angles[ind2], angles[ind1], way)) {
                bool = true;
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



 