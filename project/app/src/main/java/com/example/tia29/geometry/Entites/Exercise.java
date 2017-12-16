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
	private ArrayList<Angle> angles;
    private ArrayList<MyPoint> points;
	private ArrayList<Triangle> triangles;
    private Context context;
    private int[] letters;//free letters for new elements

    public Exercise(Context context) {
        points = new ArrayList<MyPoint>();
		angles = new ArrayList<Angle>();
        segments = new ArrayList<Segment>();
		triangles = new ArrayList<Triangle>();
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
	public ArrayList<Angle> getAngles() {
        return angles;
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
	
	    //create new angles for added segments
    public void createNewAngles() {
        ArrayList<Segment> list = getSegments();

        String name = new String();
        MyPoint p1 = null;
        MyPoint p2 = null;
        MyPoint p3 = null;
// goes over all segments and checks if they create angles
       for (Segment i : list) {
            for (Segment j : list) {
                if (!i.equals(j)) {
                    if (i.getPoint1().equals(j.getPoint1())) {
                        p1 = i.getPoint2();
                        p2 = j.getPoint2();
                        p3 = i.getPoint1();
                    } else {
                        if (i.getPoint2().equals(j.getPoint2())) {
                            p1 = i.getPoint1();
                            p2 = j.getPoint1();
                            p3 = i.getPoint2();

                        } else {
                            if (i.getPoint2().equals(j.getPoint1())) {
                                p1 = i.getPoint1();
                                p2 = j.getPoint2();
                                p3 = i.getPoint2();
                            } else {
                                if (i.getPoint1().equals(j.getPoint2())) {
                                    p1 = i.getPoint2();
                                    p2 = j.getPoint1();
                                    p3 = i.getPoint1();
                                }
                            }
                        }
                    }
                    if (p1 != null && p2 != null && p3 != null) {

                        Segment s = new Segment(p1, p2);
                        if (!isPointsOnTheSegment(s, p3)) {
                            Angle a = new Angle(p1, p3, p2);
                    //check that angle doesn't exist
                           if (!doesAngleExist(a)) {
                                //         if (shouldAddAnAngle(a))
                                this.angles.add(a);
                                p1 = null;
                                p2 = null;
                                p3 = null;
                            }
                        }

                    }

                }


            }
        }
    }

    //does angle exist (for adding new angles)
    public boolean doesAngleExist(Angle a) {
        return getAngleByName(a.GetName()) != null;
    }

	    public Angle getAngleByName(String name) {
        for (Angle i : angles) {
            String n = i.GetName();
            if (n.charAt(1) == name.charAt(1) && n.contains(name.charAt(0) + "") && n.contains(name.charAt(2) + "")) {
                return i;
            }
        }
        return null;
    }
		    public Segment GetSegmentByName(String name) {

        for (Segment key : segments) {
            if (key.GetName().equals(name)
                    || (name.charAt(0) == key.GetName().charAt(1)
                    && name.charAt(1) == key.GetName().charAt(0)))
                return key;

        }
        return null;
    }//get segment by name
	
	    //is the point on this segment
    public boolean isPointsOnTheSegment(Segment s, MyPoint p) {
        boolean bool = false;
        MyPoint p1 = s.getPoint1();
        MyPoint p2 = s.getPoint2();
        double a = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());

        double b = p1.getY() - a * p1.getX();
        double y = a * p.getX() + b;
        if (Math.abs(y - p.getY()) <= 20)
            bool = true;

        return bool;
    }
	
	public ArrayList<Triangle> getTriangles() {
        return triangles;
    }

    public boolean doesTriangleExist(Triangle t) {
        for (Triangle triangle : triangles) {
            if (triangle.GetName().contains(t.GetName().charAt(0) + "")
                    && triangle.GetName().contains(t.GetName().charAt(1) + "")
                    && triangle.GetName().contains(t.GetName().charAt(2) + ""))
                return true;
        }
        return false;
    }
	
	//create new triangles when needed after dragging new items
    public void createNewTriangle() {
        MyPoint p1, p2, p3;
        Segment s1, s2, s3;
//goes over all angles in the exercise and checks if there are 3 angles
// that create a triangle that does not exist
       for (Angle a1 : angles) {
            for (Angle a2 : angles) {
                for (Angle a3 : angles) {
                    if (!a1.equals(a2) && !a2.equals(a3) && !a1.equals(a3)) {
                        p1 = a1.getPoints()[1];
                        p2 = a2.getPoints()[1];
                        p3 = a3.getPoints()[1];
                        s1 = getLine(p1, p2);
                        s2 = getLine(p2, p3);
                        s3 = getLine(p1, p3);
                        Angle aa1 = getAngleByName(p1.GetName() + "" + p2.GetName() + p3.GetName());
                        Angle aa2 = getAngleByName(p2.GetName() + "" + p1.GetName() + p3.GetName());
                        Angle aa3 = getAngleByName(p1.GetName() + "" + p3.GetName() + p2.GetName());
                        if (aa1 != null && aa2 != null && aa3 != null && s1 != null && s2 != null && s3 != null) {
                            if (!isPointsOnTheSegment(s1, p3)) {
                                Triangle t = new Triangle(p1, p2, p3, aa1, aa2, aa3, s1, s2, s3);
                                if (!doesTriangleExist(t)) {
                                    triangles.add(t);
                                }
                            }

                        }
                    }
                }
            }
        }
    }
	public Segment getLine(MyPoint p1, MyPoint p2) {

        for (Segment key : segments) {
            if ((key.getPoint1().equals(p2) && key.getPoint2().equals(p1)) || (key.getPoint1().equals(p1) && key.getPoint2().equals(p2)))
                return key;
        }
        return null;
    }//does line exist

	

}
