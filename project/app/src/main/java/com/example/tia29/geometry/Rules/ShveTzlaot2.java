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


public class ShveTzlaot2 implements  MyRules {
    ArrayList<String> way=new ArrayList<String>();
    Exercise exercise;
    Context context;

    @Override


    public boolean check(Exercise exercise, Item[] items) {

        Triangle t = (Triangle) items[0];
        boolean b = false;
        Angle[] angles = t.getAngles();
        ArrayList<String> way1 , way2, way3;

        way1 = exercise.getWayOfGiven1(new Given(angles[0], EAll.שווה, angles[1]));
        way2 = exercise.getWayOfGiven1(new Given(angles[1], EAll.שווה, angles[2]));
        way3 = exercise.getWayOfGiven1(new Given(angles[0], EAll.שווה, angles[2]));

        if (way1 != null && way2 != null && way3 != null) {

            Utils.addAllWay(way, way1);
            Utils.addAllWay(way,way2);
            Utils.addAllWay(way,way3);
            if(result(exercise, new Item[]{t}))
                b = true;
        }

        return b;
    }

    @Override
    public boolean result(Exercise exercise, Item[] items) {
        boolean bool=false;
        String sentence1=context.getString(R.string.shveTzlaotTlaot);
        String sentence2=context.getString(R.string.shveTzlaotZaviot);
        String sentence3=context.getString(R.string.shveTzlaot60);;
        String sentence4=context.getString(R.string.tzlaotSvhveTzlaot);
        Triangle t = (Triangle) items[0];
        Segment [] segments= t.getSegments();
        Segment s1 = segments[0];
        Segment s2 = segments[1];
        Segment s3 = segments[2];
        Angle [] angles= t.getAngles();
        Angle a1 = angles[0];
        Angle a2 = angles[1];
        Angle a3 = angles[2];

        Given g=new Given(t,EAll.שווה_צלעות);
         way.add(sentence4);
        Given g1= exercise.addGeneralGiven1(g, way);
        if(g.equals(g1 )) {
            bool = true;
        }
        way=new ArrayList<String>();
        Utils.addAllWay(way,g1.getWay1());
        way.add(sentence1);
        if( exercise.setGivenThatSegmentsEqual1(s1, s2, way))
        {
            bool = true;
        }
        if(exercise.setGivenThatSegmentsEqual1(s1, s3, way))
        {
            bool=true;
        }
        if( exercise.setGivenThatSegmentsEqual1(s2, s3, way))
        {
            bool=true;
        }
        way.remove(way.size()-1);
      way.add(sentence2);
        if(exercise.setGivenThatAnglesEqual1(a1, a2, way)){
            bool=true;
        }
        if(exercise.setGivenThatAnglesEqual1(a1, a3, way))
        {
            bool=true;
        }
        if(exercise.setGivenThatAnglesEqual1(a2, a3, way))
        {
            bool=true;
        }
        way.remove(way.size()-1);
        way.add(sentence3);
        if( exercise.setGivenValueOfAngle1(a1, 60, way))
        {
            bool=true;
        }
        if( exercise. setGivenValueOfAngle1(a2, 60, way))
        {
            bool=true;
        }
        if(exercise.setGivenValueOfAngle1(a3, 60, way))
        {
            bool=true;
        }
        return  bool;
    }

    @Override
    public boolean goOver(Exercise exercise,Context context) {
        this.context=context;
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

