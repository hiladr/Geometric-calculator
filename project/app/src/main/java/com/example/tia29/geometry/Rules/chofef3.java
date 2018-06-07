
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

import java.util.ArrayDeque;
import java.util.ArrayList;
/*
find equals angle
and after find 2 equals segments neer the angle that we found
check if maybi have more equals segment or equals angle
and check if finish to prove 

*/

public class chofef3 implements MyRules {
    Angle a1, a2;
    ArrayList<String> way = new ArrayList<String>();
    Context context;

    @Override
    public boolean check(Exercise exercise, Item[] items) {
        boolean b = false;
		//check itemes if this kind triangles
        if (items.length > 1 && items[0] instanceof Triangle && items[1] instanceof Triangle) {

            Triangle t1, t2;
            t1 = (Triangle) items[0];
            t2 = (Triangle) items[1];
            Angle[] angles1 = t1.getAngles();
            Angle[] angles2 = t2.getAngles();
            int index1, index2;

            //find equals angle
            Given g = exercise.GetGivenWithEq(new Given(angles1[0], EAll.שווה, angles2[0]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
		// sent to find 2 equals segments neer the angle that we found		
                if (ifSegmentEq(exercise, t1, t2, 0, 0)) {
                    a1 = angles1[0];
                    a2 = angles2[0];
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }

            g = exercise.GetGivenWithEq(new Given(angles1[0], EAll.שווה, angles2[1]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifSegmentEq(exercise, t1, t2, 0, 1)) {
                    a1 = angles1[0];
                    a2 = angles2[1];
					
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }
            g = exercise.GetGivenWithEq(new Given(angles1[0], EAll.שווה, angles2[2]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifSegmentEq(exercise, t1, t2, 0, 2)) {
                    a1 = angles1[0];
                    a2 = angles2[2];
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }
            g = exercise.GetGivenWithEq(new Given(angles1[1], EAll.שווה, angles2[0]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifSegmentEq(exercise, t1, t2, 1, 0)) {
                    a1 = angles1[1];
                    a2 = angles2[0];
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }
            g = exercise.GetGivenWithEq(new Given(angles1[1], EAll.שווה, angles2[1]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifSegmentEq(exercise, t1, t2, 1, 1)) {
                    a1 = angles1[1];
                    a2 = angles2[1];
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }

            g = exercise.GetGivenWithEq(new Given(angles1[1], EAll.שווה, angles2[2]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifSegmentEq(exercise, t1, t2, 1, 2)) {
                    a1 = angles1[1];
                    a2 = angles2[2];
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }

            g = exercise.GetGivenWithEq(new Given(angles1[2], EAll.שווה, angles2[0]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifSegmentEq(exercise, t1, t2, 2, 0)) {
                    a1 = angles1[2];
                    a2 = angles2[0];
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }
            g = exercise.GetGivenWithEq(new Given(angles1[2], EAll.שווה, angles2[1]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifSegmentEq(exercise, t1, t2, 2, 1)) {
                    a1 = angles1[2];
                    a2 = angles2[1];
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }

            g = exercise.GetGivenWithEq(new Given(angles1[2], EAll.שווה, angles2[2]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifSegmentEq(exercise, t1, t2, 2, 2)) {
                    a1 = angles1[2];
                    a2 = angles2[2];
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }


        }
        return b;

    }

    @Override
    public boolean result(Exercise exercise, Item[] items) {
        boolean b = false;
        if (items[0] instanceof Triangle && items[1] instanceof Triangle) {
            Triangle t1 = (Triangle) items[0];
            Triangle t2 = (Triangle) items[1];
            String sentence = context.getString(R.string.chofef3);
            way.add(sentence);

            Given g = new Given(t1, EAll.חופף, t2);
            Given g1 = exercise.addGeneralGiven1(g, way);
            if (g.equals(g1)) {
                b = true;
            }
            way = new ArrayList<String>();
            Utils.addAllWay(way, g1.getWay1());
            way.add(context.getString(R.string.chambachash));
			//check if maybi have more equals segment 
            Segment s1 = exercise.getLine(a1.getPoints()[0], a1.getPoints()[2]);
            Segment s2 = exercise.getLine(a2.getPoints()[0], a2.getPoints()[2]);
            if (s1 != null && s2 != null) {
                if (exercise.setGivenThatSegmentsEqual1(s1, s2, way)) {
                    b = true;
                }
            }
            Angle aa1 = exercise.getAngleByName(a1.getPoints()[1].GetName() + a1.getPoints()[0].GetName() + a1.getPoints()[2].GetName());
            Angle aa2 = exercise.getAngleByName(a2.getPoints()[1].GetName() + a2.getPoints()[0].GetName() + a2.getPoints()[2].GetName());
			//check if maybi have more equals angle
            if (aa1 != null && aa2 != null) {
                if (exercise.setGivenThatAnglesEqual1(aa1, aa2, way)) {
                    b = true;
                }
            }
            aa1 = exercise.getAngleByName(a1.getPoints()[1].GetName() + a1.getPoints()[2].GetName() + a1.getPoints()[0].GetName());
            aa2 = exercise.getAngleByName(a2.getPoints()[1].GetName() + a2.getPoints()[2].GetName() + a2.getPoints()[0].GetName());
            if (aa1 != null && aa2 != null) {
                if (exercise.setGivenThatAnglesEqual1(aa1, aa2, way)) {
                    b = true;
                }
            }
        }
        return b;
    }
//sent exercise agin whith new result
    @Override
    public boolean goOver(Exercise exercise, Context context) {
        boolean b = false;
        this.context = context;
        ArrayList<Triangle> triangles = exercise.getTriangles();
        int size = triangles.size();
        Triangle tri1, tri2;
        for (int i = 0; i < size; i++) {
            tri1 = triangles.get(i);
            for (int j = i; j < size; j++) {
                tri2 = triangles.get(j);
                if (!tri1.equals(tri2))
                    if (check(exercise, new Item[]{tri1, tri2}))
                        b = true;
            }
        }
        return b;
    }
//find 2 equals segments neer the angle that we found
    public boolean ifSegmentEq(Exercise exercise, Triangle t1, Triangle t2, int index1, int index2) {
        Angle a1 = t1.getAngles()[index1];
        Angle a2 = t2.getAngles()[index2];

        Segment s1 = exercise.getLine(a1.getPoints()[0], a1.getPoints()[1]);

        Segment s2 = exercise.getLine(a1.getPoints()[1], a1.getPoints()[2]);

        Segment s3 = exercise.getLine(a2.getPoints()[0], a2.getPoints()[1]);

        Segment s4 = exercise.getLine(a2.getPoints()[1], a2.getPoints()[2]);

        Given g1 = exercise.GetGivenWithEq(new Given(s1, EAll.שווה, s3));
        Given g2 = exercise.GetGivenWithEq(new Given(s2, EAll.שווה, s4));
        if (g1 != null && g2 != null) {
            Utils.addAllWay(way, g1.getWay1());
            Utils.addAllWay(way, g2.getWay1());
            return true;
        }
        g1 = exercise.GetGivenWithEq(new Given(s1, EAll.שווה, s4));
        g2 = exercise.GetGivenWithEq(new Given(s2, EAll.שווה, s3));
        if (g1 != null && g2 != null) {
            Utils.addAllWay(way, g1.getWay1());
            Utils.addAllWay(way, g2.getWay1());
            return true;
        }
        return false;
    }
}
