package com.example.tia29.geometry.Entites;

/**
 * Created by Naomi on 14/12/2017.
 */
public class Segment implements Item {

    private  int id;
    private static int idIndex=100;
    private double value=-1;
    private MyPoint Point1;
    private MyPoint Point2;
    private String name;

    public Segment( MyPoint p1, MyPoint p2){
        id=idIndex++;

        Point1=p1;
        Point2=p2;
        name=p1.GetName()+p2.GetName();

    }

    @Override
    public String GetName() {
        return  name;
    }

    @Override
    public String toString() {
        return "|"+GetName();
    }

    public void setPoint1(MyPoint idPoint1) {
        this.Point1 = idPoint1;
    }
    public void setPoint2(MyPoint idPoint2) {
        this.Point2 = idPoint2;
    }

    public MyPoint getPoint1() {
        return Point1;
    }
    public MyPoint getPoint2() {
        return Point2;
    }


    public int getId() {
        return id;
    }

    public void setValue(double value) {
        this.value = value;
    }
    public double getValue() {
        return value;
    }

    //gets the other point in the segment
    public MyPoint getOtherPoint(MyPoint point){

        if(Point1.equals(point)){
            return Point2;
        }
        return Point1;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Point1 == null) ? 0 : Point1.hashCode());
        result = prime * result + ((Point2 == null) ? 0 : Point2.hashCode());
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Segment other = (Segment) obj;
        if (Point1 == null) {
            if (other.Point1 != null)
                return false;
        } else if (!Point1.equals(other.Point1))
            return false;
        if (Point2 == null) {
            if (other.Point2 != null)
                return false;
        } else if (!Point2.equals(other.Point2))
            return false;

        if (id != other.id)
            return false;
        return true;
    }



}