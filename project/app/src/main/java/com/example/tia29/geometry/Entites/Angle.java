package com.example.tia29.geometry.Entites;

/**
 * Created by Naomi on 14/12/2017.
 */
public class Angle implements Item {
    private  int id;
    private static int idIndex=1000;
    private String name;
    private MyPoint[] points;
    private double value=-1;

    public Angle(MyPoint p1,MyPoint p2, MyPoint p3){
        points=new MyPoint[3];
        points[0]=p1;
        points[1]=p2;
        points[2]=p3;
        id=idIndex++;
        name= points[0 ].GetName()+ points[1].GetName()+  points[2].GetName();
    }
    public void setValue(double val){
        value=val;
    }
    @Override
    public String GetName() {
        return name;
    }
    public MyPoint[] getPoints() {
        return points;
    }

    @Override
    public String toString() {
      return "<"+ GetName();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Angle other = (Angle) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }



}
