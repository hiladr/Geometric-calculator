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
    private ArrayList<Given> givenList;
    private Context context;
	private Given prove;
    private int[] letters;//free letters for new elements

    public Exercise(Context context) {
		givenList = new ArrayList<Given>();
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
	 public void setProve(Given prove) {
        this.prove = prove;
    }
	    
	public ArrayList<Segment> getSegments() {
        return segments;
    }
	public Given getProve() {
		  return prove;
		  }

	 public void freeLetter(char c) {

        if ((c - 'a') >= 0 && (c - 'a') < 26)
            letters[c - 'a'] = 0;
    }//free a not needed letter
	public ArrayList<Given> getGivenList() {
        return givenList;
    }

	
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
	public ArrayList<String> GetElementsNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Segment key : segments) {
            names.add("|" + key.GetName());
        }
        for (Angle key : angles) {
            names.add("<" + key.GetName());
        }

        for (Triangle key : triangles) {
            names.add("^" + key.GetName());
        }

        return names;
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
	// בודקת האם הנתון הוכח
    public ArrayList<String> getWayOfGiven1(Given given) {
        Given given1 = getGivenInExercise(given);
        if (given1 != null)
            return given1.getWay1();
        return null;
    }


   //get a given hat exists in the exercise
    public Given getGivenInExercise(Given given) {

        for (Given giv : this.getGivenList()) {
            if (given.getOperand() == EAll.ערך_כלשהו && giv.getItem1().equals(given.getItem1()) && giv.getValue() >= 0) {
                return giv;
            }
            if ((given.getOperand().equals(giv.getOperand()) && given
                    .getValue() == giv.getValue())
                    && ((given.getItem2() == null && given.getItem1() != null
                    && given.getItem1().equals(giv.getItem1()) || ((given
                    .getItem1() != null
                    && given.getItem1().equals(giv.getItem1())
                    && given.getItem2() != null
                    && given.getItem2().equals(giv.getItem2()) || (given
                    .getItem1() != null
                    && given.getItem1().equals(giv.getItem2())
                    && given.getItem2() != null && given.getItem2()
                    .equals(giv.getItem1()))))))) {

                return giv;
            }
            if (given.getItem1().equals(giv.getItem1()) && given.getOperand().equals(giv.getOperand()) && giv.getOperand() == EAll.שווה_שוקיים) {
                return giv;
            }

        }

        return null;
    }

//enter a given that two segments equal
    public boolean setGivenThatSegmentsEqual1(Segment s1, Segment s2, ArrayList<String> way) {

        Given tempGiven = new Given(s1, EAll.שווה, s2);

        boolean b = false;
        Given g = addGeneralGiven1(tempGiven, way);
        if (tempGiven.equals(g)) {
            b = true;
        }
        return b;
    }
//set a given that two angles equal
    public boolean setGivenThatAnglesEqual1(Angle a1, Angle a2, ArrayList<String> way) {
        boolean b = false;
        Given tempGiven = new Given(a1, EAll.שווה, a2);
        Given g = addGeneralGiven1(tempGiven, way);
        if (tempGiven.equals(g)) {
            b = true;
        }
        return b;

    }
//set a given with a value to a segment
    public boolean setGivenValueOfSegment1(Segment s1, double Value, ArrayList<String> way) {
        boolean wasChanged = false;
        if (Value >= 0) {
            s1.setValue(Value);
            Given tempGiv = new Given(s1, EAll.שווה, Value);
            Given g = addGeneralGiven1(tempGiv, way);
            if (tempGiv.equals(g)) {
                wasChanged = true;
            }
        }
        return wasChanged;

    }
//set a given of a value to a segment
    public boolean setGivenValueOfAngle1(Angle a1, double value, ArrayList<String> way) {
        if (value >= 0 && value <= 180) {
            a1.setValue(value);
            Given tempGiv = new Given(a1, EAll.שווה, value);
            Given g = addGeneralGiven1(tempGiv, way);
            if (tempGiv.equals(g)) {
                return true;
            }
        }
        return false;

    }
	 public Given addGeneralGiven1(Given given, ArrayList<String> way) {
        ArrayList<String> w = new ArrayList<String>();
        //   w.addAll(way);
        Utils.addAllWay(w, way);
        String s = context.getString(R.string.thereFor);
        w.add(s);
        s = given.toString();
        w.add(s);
        Given tempGiven = getGivenInExercise(given);
        if (tempGiven != null) {
            if (tempGiven.getLengthOfGiven() > Utils.lengthOfWay(w)) {
                tempGiven.setNewWay(w);
                return tempGiven;
            }
            return tempGiven;
        } else {
            given.setWay(w);
            this.addGiven(given);
            return given;
        }
    }
	
	// /are segments on the same line
    public boolean areSegmentsOnSameSegment(Segment s1, Segment s2) {
        boolean b = false;
        MyPoint p1 = s1.getPoint1();
        MyPoint p2 = s1.getPoint2();
        MyPoint p3 = s2.getPoint1();
        MyPoint p4 = s2.getPoint2();
        float a1 = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        float b1 = p1.getY() - a1 * p1.getX();
        float a2 = (p3.getY() - p4.getY()) / (p3.getX() - p4.getX());
        float b2 = p3.getY() - a2 * p3.getX();
        if ((a1 == a2) && (b2 == b1))

            b = true;
        return b;
    }

    //are segments on the same line - not exact
    public boolean areSegmentsOnSameSegmentAround(Segment s1, Segment s2) {
        boolean b = false;
        MyPoint p1 = s1.getPoint1();
        MyPoint p2 = s1.getPoint2();
        MyPoint p3 = s2.getPoint1();
        MyPoint p4 = s2.getPoint2();
        float a1 = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        float b1 = p1.getY() - a1 * p1.getX();
        float a2 = (p3.getY() - p4.getY()) / (p3.getX() - p4.getX());
        float b2 = p3.getY() - a2 * p3.getX();

        if (Math.abs(a1 - a2) <= 2 && Math.abs(b2 - b1) <= 8)
            b = true;
        return b;
    }

    //is the angle the same angle
    public boolean areAnglesIsTheSameAngle(Angle a1, Angle a2) {
        boolean b = false;
        Segment s1 = getLine(a1.getPoints()[0], a1.getPoints()[1]);
        Segment s2 = getLine(a1.getPoints()[1], a1.getPoints()[2]);

        Segment s3 = getLine(a2.getPoints()[0], a2.getPoints()[1]);
        Segment s4 = getLine(a2.getPoints()[1], a2.getPoints()[2]);

        MyPoint pMiddle = a1.getPoints()[1], p1 = null, p2 = null,
                p3 = null,
                p4 = null;

        if ((areSegmentsOnSameSegmentAround(s1, s4) && areSegmentsOnSameSegmentAround(s2, s3))) {
            p1 = a1.getPoints()[0];
            p2 = a2.getPoints()[2];
            p3 = a1.getPoints()[2];
            p4 = a2.getPoints()[0];
        }

        if ((areSegmentsOnSameSegmentAround(s1, s3) && areSegmentsOnSameSegmentAround(s2, s4))) {
            p1 = a1.getPoints()[0];
            p3 = a1.getPoints()[2];
            p2 = a2.getPoints()[0];
            p4 = a2.getPoints()[2];
        }
        if (p1 != null && p2 != null && p3 != null && p4 != null) {
            if (((p1.getX() <= pMiddle.getX() && p2.getX() <= pMiddle.getX()) || (p1.getX() >= pMiddle.getX() && p2.getX() >= pMiddle.getX()))
                    && ((p1.getY() <= pMiddle.getY() && p2.getY() <= pMiddle.getY()) || (p1.getY() >= pMiddle.getY() && p2.getY() >= pMiddle.getY()))) {
                if ((p3.getX() <= pMiddle.getX() && p4.getX() <= pMiddle.getX()) || (p3.getX() >= pMiddle.getX() && p4.getX() >= pMiddle.getX())
                        && ((p3.getY() <= pMiddle.getY() && p4.getY() <= pMiddle.getY()) || (p3.getY() >= pMiddle.getY() && p4.getY() >= pMiddle.getY()))) {
                    b = true;
                }
            }


        }


        return b;
    }
//get an angle that is between segments in  atriangle
    public Angle getAngleForSegemntsInTriangle( Triangle t, Segment s1, Segment s2) {

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

        Angle al = this.getAngleByName(name);
        return al;

    }
//get a segment that is between two angles
    public Segment getSegmentForAngleInTriangle(Triangle t, Angle a, Angle b) {

        MyPoint p1 = a.getPoints()[1];
        MyPoint p2 = b.getPoints()[1];
        Segment s = GetSegmentByName(p1.GetName() + p2.GetName());

        return s;
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
	    public MyPoint GetPointByName(char name) {

        for (MyPoint key : points) {
            if (key.GetName().charAt(0) == name)
                return key;

        }
        return null;
    }

    public Item getElementByName(String name) {
        Set keys;
        if (name != null) {
            switch (name.charAt(0)) {
                case '^':
                    for (Triangle key : triangles) {
                        if (name.contains(key.GetName())) return (key);

                    }
                    break;

                case '<':
                    for (Angle key : angles) {
                        if (name.contains(key.GetName())) return (key);

                    }

                    break;
                case '|':
                    for (Segment key : segments) {
                        if (name.contains(key.GetName())) return (key);

                    }
                    break;

            }

        }
        return null;
    }

    public void addGiven(Given given) {
        givenList.add(given);
    }

    public Segment getSegmentById(int id) {
        for (Segment key : segments) {
            if (key.getId() == id)
                return key;
        }
        return null;

    }

    public Angle getAngleById(int id) {
        for (Angle key : angles) {
            if (key.getId() == id)
                return key;
        }
        return null;

    }
	    public ArrayList<String> getTrianglesNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Triangle key : triangles) {
            names.add("^" + key.GetName());
        }

        return names;
    }

    public ArrayList<String> getAnglesNames() {
        ArrayList<String> names = new ArrayList<String>();
        Set keys;
        for (Angle key : angles) {
            names.add("<" + key.GetName());
        }


        return names;
    }

    public ArrayList<String> getSegmentsNames() {
        ArrayList<String> names = new ArrayList<String>();

        for (Segment key : segments) {
            names.add("|" + key.GetName());

        }

        return names;
    }
	 public void deleteGiven(int index) {

        Given given = givenList.remove(index);
        if (given.getOperand() == EAll.שווה && given.getValue() > 0) {
            if (given.getItem1() instanceof Segment) {
                Segment s = (Segment) given.getItem1();
                s.setValue(-1);
            }
            if (given.getItem1() instanceof Segment) {
                Angle a = (Angle) given.getItem1();
                a.setValue(-1);
            }
        }
    }
	
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
	public boolean isPointsOnTheSegmentExact(Segment s, MyPoint pMiddle) {

        boolean bool = isPointsOnTheSegment(s, pMiddle);
        MyPoint p1 = s.getPoint1();
        MyPoint p2 = s.getPoint2();
        if (bool && !(((p1.getX() <= pMiddle.getX() && p2.getX() <= pMiddle.getX()) || (p1.getX() >= pMiddle.getX() && p2.getX() >= pMiddle.getX()))
                && ((p1.getY() <= pMiddle.getY() && p2.getY() <= pMiddle.getY()) || (p1.getY() >= pMiddle.getY() && p2.getY() >= pMiddle.getY())))) {

            bool = true;
        } else {
            if (p1.equals(pMiddle) || p2.equals(pMiddle))
                bool = true;
            else bool = false;
        }

        return bool;
    }

	
	public String getNewSegmentName() {
        char l1 = findLetter();
        char l2 = findLetter();

        return l1 + "" + l2 + "";
    }//get a name for a new segment
	
	public ArrayList<Triangle> getTriangles() {
        return triangles;
    }
	
	    public String getNewTriangleName() {
        char l1 = findLetter();
        char l2 = findLetter();
        char l3 = findLetter();
        return l1 + "" + l2 + "" + l3 + "";
    }//get a new name for a triangle
	
    public String onDragTriangle(MyPoint p1, MyPoint p2, MyPoint p3) {
		
		
        points.add(p1);
        points.add(p2);
        points.add(p3);
        String name;


        // Segment s1 = new Segment(p1, p2, this);
        //segments.add(s1);
        name = onDragSegment(p1, p2);
        Segment s1 = GetSegmentByName(name);
        //Segment s2 = new Segment(p1, p3, this);
        //segments.add(s2);
        name = onDragSegment(p1, p3);
        Segment s2 = GetSegmentByName(name);
        //s3 = new Segment(p3, p2, this);
        //segments.add(s3);
        name = onDragSegment(p3, p2);
        Segment s3 = GetSegmentByName(name);
        Angle a1 = new Angle(p1, p2, p3);
        angles.add(a1);
        Angle a2 = new Angle(p2, p1, p3);
        angles.add(a2);
        Angle a3 = new Angle(p1, p3, p2);
        angles.add(a3);
        Triangle triangle = new Triangle(p1, p2, p3, a1, a2, a3, s1, s2, s3);
        triangles.add(triangle);
        return triangle.GetName();
    }//add a dragged triangle

    public boolean doesTriangleExist(Triangle t) {
        for (Triangle triangle : triangles) {
            if (triangle.GetName().contains(t.GetName().charAt(0) + "")
                    && triangle.GetName().contains(t.GetName().charAt(1) + "")
                    && triangle.GetName().contains(t.GetName().charAt(2) + ""))
                return true;
        }
        return false;
    }
	
	public Triangle getTriangleByName(String name) {
        if (name.charAt(0) != name.charAt(1) && name.charAt(1) != name.charAt(2) && name.charAt(0) != name.charAt(2)) {
            for (Triangle key : triangles) {
                if (key.GetName().contains(name.charAt(0) + "") && key.GetName().contains(name.charAt(1) + "") && key.GetName().contains(name.charAt(2) + ""))
                    return key;
            }
        }
        return null;
    }//get atriangle by name
	
	public boolean doesLineExist(MyPoint p1, MyPoint p2) {
        boolean b = false;
        if (getLine(p1, p2) != null) b = true;
        return b;
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
