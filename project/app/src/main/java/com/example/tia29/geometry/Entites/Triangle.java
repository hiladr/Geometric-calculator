package com.example.tia29.geometry.Entites;

/**
 * Created by Naomi on 16/12/2017.
 */
public class Triangle implements Item {
    private int id;
    private String name;
    private  int [ ] items;

    private MyPoint[] points;
    private Angle[] angles;
    private Segment[] segments;
    private static int idIndex=10000;

    public Triangle(MyPoint p1,MyPoint p2,MyPoint p3,Angle a1,Angle a2,Angle a3 ,Segment s1,Segment s2,Segment s3) {
        points =new MyPoint[3];
        segments =new Segment[3];
        angles =new Angle[3];
        points[0] = p1;
        points[1] = p2;
        points[2] =p3;
        angles[0] = a1;
        angles[1] = a2;
        angles[2] = a3;
        segments[0] = s1;
        segments[1] = s2;
        segments[2] = s3;

        name= points[0].GetName()+points[1].GetName()+ points[2].GetName();
        id=idIndex++;
    }
    @Override
    public String GetName() {
        return name;
    }

    public int getId() {
        return id;
    }
    public Angle[] getAngles() {
        return angles;
    }

    public Segment[] getSegments() {
        return segments;
    }

    public MyPoint[] getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "^"+GetName();
    }






}
