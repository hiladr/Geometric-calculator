package com.example.tia29.geometry.Rules;

import android.content.Context;

import com.example.tia29.geometry.Entites.Angle;
import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.Given;
import com.example.tia29.geometry.Entites.Item;
import com.example.tia29.geometry.Entites.Segment;
import com.example.tia29.geometry.Entites.Triangle;
import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.Utils;

import java.util.ArrayList;
/*
find 2  equals segments or 2 equals angles
*/


public class Equals implements MyRules {
    ArrayList<String > way=new ArrayList<String>();
    Exercise exercise;
    Context context;
    @Override
	
	//sent exercise and itemes to check if this kind segment
	
    public boolean check(Exercise exercise, Item[] items) {
        if (items[0] instanceof Segment && items[1] instanceof Segment) {

            Segment s1 = (Segment) items[0];
            Segment s2 = (Segment) items[1];
       //check if this segments(s1,s2) equals
            if (s1.getValue() == s2.getValue() && s1.getValue()!=-1) {
                ArrayList<String> way1 = exercise.getWayOfGiven1(new Given(s1, EAll.שווה, s1.getValue()));
                ArrayList<String> way2 = exercise.getWayOfGiven1(new Given(s2, EAll.שווה, s2.getValue()));
                Utils.addAllWay(way, way1);
                Utils.addAllWay(way,way2);
                return  result(exercise,new Item[]{s1,s2});

            }

        }
		
		//sent exercise and itemes to check if this kind angle
        if (items[0] instanceof Angle && items[1] instanceof Angle) {
            Angle a1 = (Angle) items[0];
            Angle a2 = (Angle) items[1];
      //check if this angles(a1,a2) equals
            if (a1.getValue() == a2.getValue() &&a1.getValue()!=-1) {
                ArrayList<String> way1 = exercise.getWayOfGiven1(new Given(a1, EAll.שווה, a1.getValue()));
                ArrayList<String > way2 = exercise.getWayOfGiven1(new Given(a2, EAll.שווה, a2.getValue()));
                Utils.addAllWay(way,way1);
                Utils.addAllWay(way,way2);
                return  result(exercise,new Item[]{a1,a2});
            }


        }
        return false;
    }

    @Override
//	check if this inghaf to prove
    public boolean result(Exercise exercise, Item[] items) {
        boolean aboolean=false;
        Given tempGiven=null;
if(items[0] instanceof  Segment ) {
    Segment s1 = (Segment) items[0];
    Segment s2 = (Segment) items[1];
    tempGiven = new Given(s1, EAll.שווה, s2);
}
        if(items[0] instanceof Angle) {
            Angle a1=(Angle)items[0];
            Angle a2=(Angle)items[1];
            tempGiven = new Given(a1, EAll.שווה, a2);
        }
    Given g = exercise.addGeneralGiven1(tempGiven, way);
    if (g.equals(tempGiven)) {
        aboolean= true;
    }

        return aboolean;
    }

    @Override
	
    public boolean goOver(Exercise exercise, Context context) {

        this.context=context;
        boolean b = false;
        ArrayList<Angle>angles=exercise.getAngles();
        int size=angles.size();
       Angle a1,a2;
        for (int i=0;i<size;i++) {
            a1=angles.get(i);
            for (int j=i;j<size;j++) {
                a2=angles.get(j);
                if (!a1.equals(a2)) {
                    boolean temp = check(exercise, new Item[]{a1, a2});
                    if (temp)
                        b = true;
               }
            }

        }

        for (Segment s1 : exercise.getSegments()) {
            for (Segment s2 : exercise.getSegments()) {
                if (!s1.equals(s2)) {
                    boolean temp = check(exercise, new Item[]{s1, s2});
                    if (temp)
                        b = true;
               }
            }


        }
        return b;
    }
}







