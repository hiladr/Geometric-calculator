package com.example.tia29.geometry.Rules;

import android.content.Context;

import com.example.tia29.geometry.Entites.*;
import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.R;

import org.apache.http.impl.conn.tsccm.WaitingThread;

import java.util.ArrayList;

public class SumOfAngles implements MyRules {

    Context context;
    ArrayList<String> way = new ArrayList<String>();

    @Override
    public boolean check(Exercise exercise, Item[] items) {
        if (items[0] instanceof Triangle) {
            return result(exercise, items);
        }
        return false;
    }

    @Override
    public boolean result(Exercise exercise, Item[] items) {
        boolean b = false;
        Triangle t = (Triangle) items[0];
       // Given tempGiven = null;
        ArrayList<String> way1, way2;
        String sentence = context.getString(R.string.sumOfAngles);

        Angle a1 = t.getAngles()[0];
        Angle a2 = t.getAngles()[1];
        Angle a3 = t.getAngles()[2];
        //Check if the angles have value in the last two angles
        if (a1.getValue() > 0 && a2.getValue() > 0 && a3.getValue() <= 0) {
            way1 = exercise.getWayOfGiven1(new Given(a1, EAll.שווה, a1.getValue()));
            way2 = exercise.getWayOfGiven1(new Given(a2, EAll.שווה, a2.getValue()));
            if (way1 != null && way2 != null) {
                way = new ArrayList<String>();
                way.addAll(way1);
                way.addAll(way2);
                way.add(sentence);
                if (exercise.setGivenValueOfAngle1(a3, 180 - a1.getValue() - a2.getValue(), way))
                    b = true;

            }
        }
        //Check if the angles have value in the last two angles
        if (a2.getValue() > 0 && a3.getValue() > 0 && a1.getValue() <= 0) {
            way1 = exercise.getWayOfGiven1(new Given(a3, EAll.שווה, a3.getValue()));
            way2 = exercise.getWayOfGiven1(new Given(a2, EAll.שווה, a2.getValue()));
            if (way1 != null && way2 != null) {
                way = new ArrayList<String>();
                way.addAll(way1);
                way.addAll(way2);
                way.add(sentence);
                if (exercise.setGivenValueOfAngle1(a1, 180 - a2.getValue() - a3.getValue(), way))
                    b = true;

            }
        }
        //Check if the angles have value in the last two angles
        if (a1.getValue() > 0 && a3.getValue() > 0 && a2.getValue() <= 0) {
            way1 = exercise.getWayOfGiven1(new Given(a1, EAll.שווה, a1.getValue()));
            way2 = exercise.getWayOfGiven1(new Given(a3, EAll.שווה, a3.getValue()));
            if (way1 != null && way2 != null) {
                way = new ArrayList<String>();
                way.addAll(way1);
                way.addAll(way2);
                way.add(sentence);
                if (exercise.setGivenValueOfAngle1(a2, 180 - a1.getValue() - a3.getValue(), way))
                    b = true;


            }
        }
        //Check if the angles have value in the last two anglesת
        if (a1.getValue() > 0 && a3.getValue() > 0 && a2.getValue() <= 0) {
            way1 = exercise.getWayOfGiven1(new Given(a1, EAll.שווה, a1.getValue()));
            way2 = exercise.getWayOfGiven1(new Given(a3, EAll.שווה, a3.getValue()));
            if (way1 != null && way2 != null) {
                way = new ArrayList<String>();
                way.addAll(way1);
                way.addAll(way2);
                way.add(sentence);
                if (exercise.setGivenValueOfAngle1(a2, 180 - a1.getValue() - a3.getValue(), way))
                    b = true;


            }
        }
		//Checking if there is value in some angle, and if they are equal
        //בדיקה האם יש ערך כלשהו באחת הזוויות והזוויות האחרות שוות בינהן
        if (exercise.getWayOfGiven1(new Given(a1, EAll.שווה, a2)) != null && a3.getValue() > 0) {
            way1 = exercise.getWayOfGiven1(new Given(a1, EAll.שווה, a2));
            way2 = exercise.getWayOfGiven1(new Given(a3, EAll.שווה, a3.getValue()));
            if (way1 != null && way2 != null) {
                way = new ArrayList<String>();
                way.addAll(way1);
                way.addAll(way2);
                way.add(sentence);
                if (exercise.setGivenValueOfAngle1(a2, (180 - a3.getValue()) / 2, way)) b = true;
                if (exercise.setGivenValueOfAngle1(a1, (180 - a3.getValue()) / 2, way)) b = true;


            }
        }
       //Checking if there is value in some angle, and if they are equal
        if (exercise.getWayOfGiven1(new Given(a2, EAll.שווה, a3)) != null && a1.getValue() > 0) {
            way1 = exercise.getWayOfGiven1(new Given(a2, EAll.שווה, a3));
            way2 = exercise.getWayOfGiven1(new Given(a1, EAll.שווה, a1.getValue()));
            if (way1 != null && way2 != null) {
                way = new ArrayList<String>();
                way.addAll(way1);
                way.addAll(way2);
                way.add(sentence);
                if (exercise.setGivenValueOfAngle1(a2, (180 - a1.getValue()) / 2, way)) b = true;
                if (exercise.setGivenValueOfAngle1(a3, (180 - a1.getValue()) / 2, way)) b = true;


            }
        }
    //Checking if there is value in some angle, and if they are equal
	
	
        if (exercise.getWayOfGiven1(new Given(a1, EAll.שווה, a3)) != null && a2.getValue() > 0) {
            way1 = exercise.getWayOfGiven1(new Given(a1, EAll.שווה, a3));
            way2 = exercise.getWayOfGiven1(new Given(a2, EAll.שווה, a2.getValue()));
            if (way1 != null && way2 != null) {
                way = new ArrayList<String>();
                way.addAll(way1);
                way.addAll(way2);
                way.add(sentence);
                if (exercise.setGivenValueOfAngle1(a1, (180 - a2.getValue()) / 2, way)) b = true;
                if (exercise.setGivenValueOfAngle1(a2, (180 - a2.getValue()) / 2, way)) b = true;


            }
        }


        return b;
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

