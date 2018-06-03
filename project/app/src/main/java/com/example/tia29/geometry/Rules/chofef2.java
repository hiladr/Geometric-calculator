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


public class chofef2 implements MyRules {
    ArrayList<String> way = new ArrayList<String>();
    Context context;
    char c1 = ' ';
    char c2 = ' ';
    int index1, index2;
    Angle aa1, aa2, aa3, aa4;
    @Override
    public boolean check(Exercise exercise, Item[] items) {
        boolean b = false;
        if (items.length > 1 && items[0] instanceof Triangle && items[1] instanceof Triangle) {
            Triangle t1, t2;
            t1 = (Triangle) items[0];
            t2 = (Triangle) items[1];
            Segment[] segments1 = t1.getSegments();
            Segment[] segments2 = t2.getSegments();
            //int index1,index2;

            //finde equals segments
            Given g = exercise.GetGivenWithEq(new Given(segments1[0], EAll.שווה, segments2[0]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
			// sent to find the '3' char and constarctor angle	
                if (ifAngleEq(exercise, t1, t2, 0, 0)) {
                    index1 = 0;
                    index2 = 0;

                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }
            g = exercise.GetGivenWithEq(new Given(segments1[0], EAll.שווה, segments2[1]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifAngleEq(exercise, t1, t2, 0, 1)) {
                    index1 = 0;
                    index2 = 1;
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }


            g = exercise.GetGivenWithEq(new Given(segments1[0], EAll.שווה, segments2[2]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifAngleEq(exercise, t1, t2, 0, 2)) {
                    index1 = 0;
                    index2 = 2;
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }

            g = exercise.GetGivenWithEq(new Given(segments1[1], EAll.שווה, segments2[0]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifAngleEq(exercise, t1, t2, 1, 0)) {
                    index1 = 1;
                    index2 = 0;
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }
            g = exercise.GetGivenWithEq(new Given(segments1[1], EAll.שווה, segments2[1]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifAngleEq(exercise, t1, t2, 1, 1)) {
                    index1 = 1;
                    index2 = 1;
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }

            g = exercise.GetGivenWithEq(new Given(segments1[1], EAll.שווה, segments2[2]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifAngleEq(exercise, t1, t2, 1, 2)) {
                    index1 = 1;
                    index2 = 2;
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }

            g = exercise.GetGivenWithEq(new Given(segments1[2], EAll.שווה, segments2[0]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifAngleEq(exercise, t1, t2, 2, 0)) {
                    index1 = 2;
                    index2 = 0;
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }
            g = exercise.GetGivenWithEq(new Given(segments1[2], EAll.שווה, segments2[1]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifAngleEq(exercise, t1, t2, 2, 1)) {
                    index1 = 2;
                    index2 = 1;
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }

            g = exercise.GetGivenWithEq(new Given(segments1[2], EAll.שווה, segments2[2]));
            if (g != null) {
                way = new ArrayList<String>();
                Utils.addAllWay(way, g.getWay1());
                if (ifAngleEq(exercise, t1, t2, 2, 2)) {
                    index1 = 2;
                    index2 = 2;
                    if (result(exercise, new Item[]{t1, t2})) b = true;
                }
            }
        }
        return b;

    }

    @Override
    public boolean result(Exercise exercise, Item[] items) {
        boolean b = false;

        if (items.length > 1 && items[0] instanceof Triangle && items[1] instanceof Triangle) {
            Triangle t1 = (Triangle) items[0];
            Triangle t2 = (Triangle) items[1];

            String sentence = context.getString(R.string.chofef2);
            way.add(sentence);

            Given g = new Given(t1, EAll.חופף, t2);
             //add given to exercise
            Given g1 = exercise.addGeneralGiven1(g, way);
            if (g.equals(g1))
                b = true;
            way = new ArrayList<String>();
            Utils.addAllWay(way, g1.getWay1());
            way.add(context.getString(R.string.chambachash));
            Segment s1 = exercise.getLine(aa1.getPoints()[0], aa1.getPoints()[2]);
            Segment s2 = exercise.getLine(aa2.getPoints()[0], aa2.getPoints()[2]);
            if (s1 != null && s2 != null) {
                if (exercise.setGivenThatSegmentsEqual1(s1, s2, way)) {
                    b = true;
                }
            }
            s1 = exercise.getLine(aa3.getPoints()[0], aa3.getPoints()[2]);
            s2 = exercise.getLine(aa4.getPoints()[0], aa4.getPoints()[2]);
            if (s1 != null && s2 != null) {

                if (exercise.setGivenThatSegmentsEqual1(s1, s2, way)) {
                    b = true;
                }
            }

            Angle a1 = exercise.getAngleByName(aa1.GetName().charAt(1) + c1 + "" + aa3.GetName().charAt(1));
            Angle a2 = exercise.getAngleByName(aa2.GetName().charAt(1) + c2 + "" + aa4.GetName().charAt(1));
            if (a1 != null && a2 != null) {
                if (exercise.setGivenThatAnglesEqual1(a1, a2, way)) {
                    b = true;
                }
            }


        }
        return b;
    }

    @Override
    public boolean goOver(Exercise exercise, Context context) {
        boolean b = false;
        this.context = context;
        Triangle tri1, tri2;
        ArrayList<Triangle> triangles = exercise.getTriangles();
        int size = triangles.size();
        for (int i = 0; i < size; i++) {
            tri1 = triangles.get(i);
            for (int j = i; j < size; j++) {
                tri2 = triangles.get(j);
                if (!tri1.equals(tri2))
				//sent to check agin with exercise and new items	
                    if (check(exercise, new Item[]{tri1, tri2}))
                        b = true;
            }
        }
        return b;
    }
   //finde equals angle
    public boolean ifAngleEq(Exercise exercise, Triangle t1, Triangle t2, int index1, int index2) {
        Segment s1 = t1.getSegments()[index1];
        Segment s2 = t2.getSegments()[index2];
		
     //  find the '3' char

        if (t1.GetName().charAt(0) != s1.GetName().charAt(0) && t1.GetName().charAt(0) != s1.GetName().charAt(1))
            c1 = t1.GetName().charAt(0);
        if (t1.GetName().charAt(1) != s1.GetName().charAt(0) && t1.GetName().charAt(1) != s1.GetName().charAt(1))
            c1 = t1.GetName().charAt(1);
        if (t1.GetName().charAt(2) != s1.GetName().charAt(0) && t1.GetName().charAt(2) != s1.GetName().charAt(1))
            c1 = t1.GetName().charAt(2);

        if (t2.GetName().charAt(0) != s2.GetName().charAt(0) && t2.GetName().charAt(0) != s2.GetName().charAt(1))
            c2 = t2.GetName().charAt(0);
        if (t2.GetName().charAt(1) != s2.GetName().charAt(0) && t2.GetName().charAt(1) != s2.GetName().charAt(1))
            c2 = t2.GetName().charAt(1);
        if (t2.GetName().charAt(2) != s2.GetName().charAt(0) && t2.GetName().charAt(2) != s2.GetName().charAt(1))
            c2 = t2.GetName().charAt(2);
		// constarctor 2 angle
        Angle a1 = exercise.getAngleByName(c1 + "" + s1.GetName().charAt(0) + "" + s1.GetName().charAt(1) + "");
        Angle a2 = exercise.getAngleByName(c1 + "" + s1.GetName().charAt(1) + "" + s1.GetName().charAt(0) + "");
        Angle a3 = exercise.getAngleByName(c2 + "" + s2.GetName().charAt(0) + "" + s2.GetName().charAt(1) + "");
        Angle a4 = exercise.getAngleByName(c2 + "" + s2.GetName().charAt(1) + "" + s2.GetName().charAt(0) + "");
        Given g1 = exercise.GetGivenWithEq(new Given(a1, EAll.שווה, a3));
        Given g2 = exercise.GetGivenWithEq(new Given(a2, EAll.שווה, a4));
		//if find equals angle add to way
        if (g1 != null && g2 != null) {
            Utils.addAllWay(way, g1.getWay1());
            Utils.addAllWay(way, g2.getWay1());
            aa1 = a1;
            aa2 = a3;
            aa3 = a2;
            aa4 = a4;
            return true;
        }
        g1 = exercise.GetGivenWithEq(new Given(a1, EAll.שווה, a4));
        g2 = exercise.GetGivenWithEq(new Given(a2, EAll.שווה, a3));
        if (g1 != null && g2 != null) {
            Utils.addAllWay(way, g1.getWay1());
            Utils.addAllWay(way, g2.getWay1());
            aa1 = a1;
            aa2 = a4;
            aa3 = a2;
            aa4 = a3;
            return true;
        }

        return false;
    }
}