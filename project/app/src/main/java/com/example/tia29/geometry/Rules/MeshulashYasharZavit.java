
 package com.example.tia29.geometry.Rules;

import android.content.Context;

import com.example.tia29.geometry.Entites.Angle;
import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.Given;
import com.example.tia29.geometry.Entites.Item;
import com.example.tia29.geometry.Entites.Triangle;
import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.Utils;

import java.util.ArrayList;
/*
get exercise and sent to check whith exercise and triangle
check if triangle yashr zavit
 and add sentence "yasharZavit"
and check if finish to prove 

*/


public class MeshulashYasharZavit implements  MyRules {
    ArrayList<String> way=new ArrayList<String>();
    Exercise exercise;
    Context context;
    @Override
    public boolean check(Exercise exercise, Item[] items) {
        boolean changed=false;
		//check itemes if this kind triangles
		
        if (items[0] instanceof Triangle) {

            Triangle triangle = (Triangle) items[0];
            Angle []angles= triangle.getAngles();
            Given giv = exercise.getGivenInExercise(new Given(triangle, EAll.ישר_זוית));
            if(giv!=null)
            {
                Angle a= (Angle) giv.getItem2();
              return   exercise.setGivenValueOfAngle1(a,90,giv.getWay1() );

            }
			//check which angle is 90
                if (angles[0].getValue() == 90) {
                Utils.addAllWay(way , exercise.getWayOfGiven1(new Given(triangle.getAngles()[0], EAll.שווה, 90)));
                 if(way!=null&& way.size()>0)
                     return result(exercise,new Item[]{triangle,angles[0]});
                }

                if (angles[1].getValue() == 90) {
                    Utils.addAllWay(way, exercise.getWayOfGiven1(new Given(triangle.getAngles()[1], EAll.שווה, 90)));
                    if(way!=null&& way.size()>0)
                      return result(exercise,new Item[]{triangle,angles[1]});
                }

                if (angles[2].getValue() == 90) {
                    Utils.addAllWay(way , exercise.getWayOfGiven1(new Given(triangle.getAngles()[2], EAll.שווה, 90)));
                    if(way!=null&& way.size()>0)
                       return result(exercise,new Item[]{triangle,angles[2]});
                }



        }
        return false;
    }

    @Override
    public boolean result(Exercise exercise, Item[] items) {
boolean b=false;
       Triangle t= (Triangle) items[0];
        Angle a= (Angle) items[1];
        Given g=new Given(t, EAll.ישר_זוית, a);
	//	add sentence about "yashr zavit "
           way.add(context.getString(R.string.yasharZavit));
          Given temp=   exercise.addGeneralGiven1(g , way);
        if(temp.equals(g))
        {
            b= true;
        }
        return b;
    }
	//get exercise and sent to check whith exercise and triangle
    @Override
    public boolean goOver(Exercise exercise, Context context) {
        this.context=context;
        ArrayList<Triangle> triangles = exercise.getTriangles();
        boolean b = false;
        for (Triangle triangle : triangles) {
            boolean temp = check(exercise, new Item[]{triangle});
            if (temp) {
                b = true;
            }
        }
        return b;
    }
}

