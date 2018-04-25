package com.example.tia29.geometry.Rules;

import android.content.Context;

import com.example.tia29.geometry.Entites.*;
import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.Utils;

import java.util.ArrayList;


public class Tzmudot implements MyRules {
   ArrayList<String>way=new ArrayList<String>();
    Exercise exercise;
    Context context;
    @Override
    public boolean check(Exercise exercise, Item[] items) {
        Angle a1= (Angle) items[0];
        Angle a2= (Angle) items[1];


        MyPoint p1 = a1.getPoints()[0];
        MyPoint p2 = a1.getPoints()[1];
        MyPoint p3 = a1.getPoints()[2];

        MyPoint q1 = a2.getPoints()[0];
        MyPoint q2 = a2.getPoints()[1];
        MyPoint q3 = a2.getPoints()[2];
        Segment s=null;
        Segment s1=null;
        boolean b=false;
        if (!a1.equals(a2)&&p2.equals(q2))
        { if((p1.equals(q1) && exercise.doesLineExist(p3, q3)))
            {
                s=exercise.getLine(p3, q3);
                 //b=true;
            }
      /*  else
        {
            s=exercise.getLine(p3, q3);
            s1=exercise.getLine(p1, q1);
        }*/
                if(p3.equals(q3) && exercise.doesLineExist(p1, q1))
                {
                    s=exercise.getLine(p1, q1);
                  //  b=true;
                }
              /*  else
                {
                    s=exercise.getLine(p1, q1);
                    s1=exercise.getLine(p3, q3);
                }*/
              if((p1.equals(q3) && exercise.doesLineExist(p3, q1)))
            {
                s=exercise.getLine(p3, q1);
      //          b=true;
            }
             /* else
              {
                  s=exercise.getLine(p3, q1);
                  s1=exercise.getLine(p1, q2);
              }*/
              if(p3.equals(q1) && exercise.doesLineExist(p1, q3)) {
                  s=exercise.getLine(p1, q3);
              //    b=true;
              }
        /* else
              {
                  s=exercise.getLine(p1, q3);
                  s1=exercise.getLine(p3, q1);
              }*/


            //if(s!=null&&exercise.isPointsOnTheSegment(s,p2)&&s1!=null&&exercise.isPointsOnTheSegment(s1,p2))
           // {
               // return result(exercise, items);
           //}
           // else {

                if (!exercise.areAnglesIsTheSameAngle(a1,a2)&&s != null && exercise.isPointsOnTheSegment(s, p2))

             //{
                    return result(exercise, items);
              //  }
            }



        return false;
    }


    @Override
    public boolean result(Exercise exercise, Item[] items) {
        Given tempGiven;
        //TODO
way=new ArrayList<String>();
        boolean wasChanghed = false;
        String sentence=      context.getString(R.string.tzmudot);
            Angle angle= (Angle) items[0];
            Angle key= (Angle) items[1];
                if (angle.getValue() != -1 ) {
                    way=new ArrayList<String>();
                     Utils.addAllWay(way, exercise.getWayOfGiven1(new Given(angle,EAll.שווה, angle.getValue())));
                    way.add(sentence);
                    if (way != null&&way.size()>0) {
                       if(exercise.setGivenValueOfAngle1(key, 180 - angle.getValue(), way))
                          wasChanghed = true;
                    }

                } else {
                    if (key.getValue() != -1 ) {
                        way=new ArrayList<String>();
                        Utils.addAllWay(way, exercise.getWayOfGiven1(new Given(key, EAll.שווה, key.getValue())));
                        way.add(sentence);
                        if (way != null&&way.size()>0)  {

                            if(exercise.setGivenValueOfAngle1(angle, 180 - key.getValue(), way))
                              wasChanghed = true;
                        }

                    }

        else {
                        ArrayList<String> tempWay = exercise.getWayOfGiven1(new Given(angle, EAll.שווה, key));
                        if (tempWay!=null) {
                            way = new ArrayList<String>();
                            Utils.addAllWay(way, tempWay);
                            way.add(sentence);
                            if (way != null && way.size() > 0) {

                                if(exercise.setGivenValueOfAngle1(angle,90, way))
                                wasChanghed=true;

                            }
                            if (way != null && way.size() > 0) {

                                if(exercise.setGivenValueOfAngle1(key,90, way))
                                    wasChanghed=true;

                            }

                        }
                    }
        }
return wasChanghed;
            }

    @Override
    public boolean goOver(Exercise exercise,Context context) {
        this.context=context;
        boolean b=false;
        Angle a1,a2;
        ArrayList<Angle>angles;
        angles=exercise.getAngles();
        int size=angles.size();
        for (int i=0;i<size;i++) {
            a1=angles.get(i);
            for (int j=i;j<size;j++) {
                a2=angles.get(j);
                     if(!a1.equals(a2)){
                boolean temp = check(exercise,new Item[]{a1,a2});
                if (temp) b = true;
            }
            }

        }
        return b;
    }


}

