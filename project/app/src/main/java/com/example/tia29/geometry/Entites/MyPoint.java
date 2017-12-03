package com.example.tia29.geometry.Entites;

import android.media.audiofx.Equalizer;

import com.example.tia29.geometry.Entites.Item;

public class MyPoint implements Item {

private String name;
private int id;
private static int  index=1;
    private  float x;
    private float y;



    public MyPoint(String name,float x, float y) {
        this.name = name;
        this.id=index++;
        this.x=x;
        this.y=y;
    }

    @Override
    public String GetName() {
        return name;
    }

    public int getId() {
        return id;
    }



    public void setName(String name) {
        this.name = name;
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyPoint)) return false;

        MyPoint myPoint = (MyPoint) o;

        if (Float.compare(myPoint.x, x) != 0) return false;
        if (Float.compare(myPoint.y, y) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }
    public boolean arePointsClose(MyPoint p)
    {
        boolean b=true;
        if(Math.abs(y - p.getY())>=15 ||Math.abs(x-p.getX())>=15)
        {
           b=false;
        }
        return  b;
    }
}
