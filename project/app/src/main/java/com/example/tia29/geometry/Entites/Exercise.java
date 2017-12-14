package com.example.tia29.geometry.Entites;

import android.content.Context;
import android.renderscript.Sampler;
import android.util.Log;

import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.UI.MainActivity;
import com.example.tia29.geometry.Utils;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Naomi on 14/12/2017.
 */
public class Exercise {

    private MainActivity mainActivity;
	private ArrayList<Segment> segments;
    private ArrayList<MyPoint> points;
    private Context context;
    private int[] letters;//free letters for new elements

    public Exercise(Context context) {
        points = new ArrayList<MyPoint>();
        letters = new int[26];
        for (int i = 0; i < 26; i++) {
            letters[i] = 0;
        }
        this.context = context;
    }
	    
	public ArrayList<Segment> getSegments() {
        return segments;
    }

	 public void freeLetter(char c) {

        if ((c - 'a') >= 0 && (c - 'a') < 26)
            letters[c - 'a'] = 0;
    }//free a not needed letter
	
    public ArrayList<MyPoint> getPoints() {
        ArrayList<MyPoint> p = new ArrayList<MyPoint>();

        for (MyPoint key : points) {
            p.add(key);

        }
        return p;
    }
	
	    public String onDragSegment(MyPoint p1, MyPoint p2) {
        if (!p1.equals(p2)) {
            if (doesPointExist(p1) == null)
                points.add(p1);
            if (doesPointExist(p2) == null)
                points.add(p2);
            Segment s = new Segment(p1, p2);
            segments.add(s);
            return s.GetName();

        }
        return null;
    }//add a dragged segment
	
	public MyPoint doesPointExist(MyPoint p) {
        boolean b = false;
        for (MyPoint point : points) {
            if (point.arePointsClose(p)) {
                return point;
            }
        }
        return null;
    }
	
	 public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

	
	    //if segments cut each other
    public boolean whenSegmentsCut(Segment s1, Segment s2) {


        MyPoint p1 = s1.getPoint1();
        MyPoint p2 = s1.getPoint2();
        MyPoint p3 = s2.getPoint1();
        MyPoint p4 = s2.getPoint2();
        if (s1.GetName().contains(s2.GetName().charAt(0) + "") || s1.GetName().contains(s2.GetName().charAt(1) + "")) {
            return false;
        }
		        //calculate line equation and find cutting point
        float a1 = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());

        float b1 = p1.getY() - a1 * p1.getX();

        float a2 = (p3.getY() - p4.getY()) / (p3.getX() - p4.getX());
        float b2 = p3.getY() - a2 * p3.getX();
        float x = (b2 - b1) / (a1 - a2);
        if (a1 == 0)
            b1 = p1.getY();
        if (a2 == 0) {
            b2 = p3.getY();
        }
        //check that the cutting point is included in the drawing segments
        if (x > 0 && ((x >= p1.getX() && x <= p2.getX()) || (x <= p1.getX() && x >= p2.getX()))
                && ((x >= p3.getX() && x <= p4.getX()) || (x <= p3.getX() && x >= p4.getX()))) {
            float y = a1 * x + b1;
//check if the cutting point already exists
            char name = findLetter();

            MyPoint p = new MyPoint(name + "", x, y);
            if (doesPointExist(p) != null) {
                freeLetter(p.GetName().charAt(0));
                p = doesPointExist(p);
            } else {
                mainActivity.addPoint(name, x, y);
            }

            if (p.equals(p1)) {
                //   freeLetter(p.GetName().charAt(0));
                p = p1;
                onDragSegment(p, p3);
                onDragSegment(p, p4);
                return true;
            }
            if (p.equals(p2)) {
                //freeLetter(p.GetName().charAt(0));
                p = p2;

                onDragSegment(p, p3);
                onDragSegment(p, p4);
                return true;
            }
            if (p.equals(p3)) {
                //freeLetter(p.GetName().charAt(0));
                p = p3;
                onDragSegment(p, p1);
                onDragSegment(p, p2);
                return true;

            }
            if (p.equals(p4)) {
                //freeLetter(p.GetName().charAt(0));
                p = p4;
                onDragSegment(p, p2);
                onDragSegment(p, p1);
                return true;

            }
            if (!(p.GetName().equals(name + "")))
                freeLetter(name);

            onDragSegment(p, p2);
            onDragSegment(p, p3);
            onDragSegment(p, p1);
            onDragSegment(p, p4);
            return true;
        }

        return false;
    }
	
	    public char findLetter() {

        for (int i = 0; i < 26; i++) {
            if (letters[i] == 0) {
                letters[i] = -1;
                return (char) (i + 'a');
            }
        }
        return '*';
    }//finds a free letter for a new element
	
		    public Segment GetSegmentByName(String name) {

        for (Segment key : segments) {
            if (key.GetName().equals(name)
                    || (name.charAt(0) == key.GetName().charAt(1)
                    && name.charAt(1) == key.GetName().charAt(0)))
                return key;

        }
        return null;
    }//get segment by name

}
