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


public class ShveShokayim2 implements MyRules {
ArrayList<String> way=new ArrayList<String>();
    Exercise exercise;
    Context context;
    @Override
    public boolean check(Exercise exercise, Item[] items) {
        boolean flag = false;
        if (items[0] instanceof Triangle) {
            Triangle t = (Triangle) items[0];
            Given g=exercise.getGivenInExercise(new Given(t, EAll.שווה_שוקיים));
            if (g!=null && g.getItem2() instanceof Angle) {
                  Utils.addAllWay(way, g.getWay1());
                   return result(exercise,new Item[]{g.getItem1(),g.getItem2(),g.getItem3()});
            }
                Angle a1=t.getAngles()[0];
                Angle a2=t.getAngles()[1];
                Angle a3=t.getAngles()[2];
                 way = new ArrayList<String>();
               Utils.addAllWay(way, exercise.getWayOfGiven1(new Given(a1, EAll.שווה, a3)));
                    if (way.size()>0) {
                   if(   result(exercise, new Item[]{t,a1, a3}))
                       flag=true;

                    }
                way = new ArrayList<String>();

              Utils.addAllWay(way, exercise.getWayOfGiven1(new Given(a1, EAll.שווה, a2)));
                    if (way.size()>0 ) {
                      if(  result(exercise, new Item[]{t, a1, a2}))   flag = true;

                    }

               way = new ArrayList<String>();
            Utils.addAllWay(way, exercise.getWayOfGiven1(new Given(a2, EAll.שווה, a3)));
                    if (way .size()>0 ) {

                        if(result(exercise, new Item[]{t,a2, a3}))flag = true;

                    }

                }
        return flag;
    }

    @Override
    public boolean result(Exercise exercise, Item[] items) {
        boolean b = false;
        if(items[0]instanceof Triangle&&items[1]instanceof Angle&&items[2]instanceof Angle) {
            Triangle t = (Triangle) items[0];
            Angle a1 = (Angle) items[1];
            Angle a2 = (Angle) items[2];

            //Segment[] segments = t.getSegments();
/*            Segment s = exercise.getLine(a1.getPoints()[1],a2.getPoints()[1]);
            int ind1 = 0, ind2 = 0;
            //אילו צלעות שוות
            if (segments[0].equals(s)) {
                ind1 = 1;
                ind2 = 2;
            }
            if (segments[1].equals(s)) {
                ind1 = 0;
                ind2 = 2;
            }
            if (segments[2].equals(s)) {
                ind1 = 0;
                ind2 = 1;
            }*/
            Segment s1=exercise.getLine(a1.getPoints()[0],a1.getPoints()[2]);
            Segment s2=exercise.getLine(a2.getPoints()[0],a2.getPoints()[2]);
            Given g = new Given(t, EAll.שווה_שוקיים, a1, a2);
            if (way == null) way = new ArrayList<String>();
            way.add(context.getString(R.string.shveShokayimZaviot));
            Given g1 = exercise.addGeneralGiven1(g, way);
            if (g.equals(g1)) b = true;
            way = new ArrayList<String>();
            Utils.addAllWay(way, g1.getWay1());

            way.add(context.getString(R.string.meshulashshveshokoimtzlaotshavot));
            if (exercise.setGivenThatSegmentsEqual1(s1, s2, way)) b = true;
        }
return b;
    }

    @Override
    public boolean goOver(Exercise exercise, Context context) {
        way=new ArrayList<String>();
        this.context=context;
        ArrayList<Triangle> triangles = exercise.getTriangles();
        boolean b = false;
        for (Triangle triangle : triangles) {
            boolean temp =check(exercise, new Item[]{triangle});
            if (temp) {
                b = true;
            }
        }
        return b;
    }
    }

