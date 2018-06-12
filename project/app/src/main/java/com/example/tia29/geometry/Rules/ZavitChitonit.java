package com.example.tia29.geometry.Rules;

import android.content.Context;
import android.util.Log;

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
/*
check that angle stay on the tringle but not in tringle
check which another point is part from triangle
check the ""three point" that is not part from angle
check that its reali "angle chitonit" and not "angle zmuda"
about checking that points reali on same segment.
add sentence about "zavit chitonit" and have all option that can to prove from this sentence
and check if finish to prove 

*/

public class ZavitChitonit implements MyRules {

    ArrayList<String>way;
    Context context;
    @Override
    public boolean check(Exercise exercise, Item[] items) {
        boolean bool = false;
 if(items[0] instanceof Triangle && items[1] instanceof Angle) {
    Triangle triangle = (Triangle) items[0];
    Angle angle = (Angle) items[1];
     Log.d("ZavitChitonit",triangle.GetName()+angle.GetName());
    String nameofTri = triangle.GetName();
    char c;
    int i;
	//the angle stay on the tringle but not in tringle
    if (nameofTri.contains(angle.GetName().charAt(1) + "")
            && !triangle.getAngles()[0].equals(angle)
            && !triangle.getAngles()[1].equals(angle)
            && !triangle.getAngles()[2].equals(angle)) {
//check which another point is part from triangle
        i = -1;
        if (nameofTri.contains(angle.GetName().charAt(0) + ""))
            i = 0;
        if (nameofTri.contains(angle.GetName().charAt(2) + ""))
            i = 2;
        if (i >= 0) {
//check the ""three point" that is not part from angle
            if (nameofTri.charAt(0) != angle.GetName().charAt(i)
                    && nameofTri.charAt(0) != angle.GetName().charAt(1)) {
                c = nameofTri.charAt(0);
            } else if (nameofTri.charAt(1) != angle.GetName().charAt(i)
                    && nameofTri.charAt(1) != angle.GetName().charAt(1)) {
                c = nameofTri.charAt(1);
            } else

             
			 c = nameofTri.charAt(2);
			 //check that its reali "angle chitonit" and not "angle zmuda"
			 //about checking that points reali on same segment.
         
            Segment s=exercise.GetSegmentByName("" + c + angle.GetName().charAt(2 - i));
            if (s!=null&&exercise.isPointsOnTheSegmentExact(s,angle.getPoints()[1])) {

                if (result(exercise, new Item[]{triangle, angle}))
                    bool = true;
            }
        }
    }
}

        return bool;
    }

    @Override
    public boolean result(Exercise exercise, Item[] items) {

boolean bool=false;
        Triangle triangle = (Triangle) items[0];
        Angle angle = (Angle) items[1];
       // Log.d("ZavitChitonitLMeshukashResult",triangle.GetName()+" "+angle.GetName());
        String sentence = context.getString(R.string.chitonit);
        int numAngle1 = 0, numAngle2 = 0;
        Given given;
        ArrayList<String> way1, way2;
		//discover which angles not thmudot to angle
        if (triangle.getAngles()[0].GetName().charAt(1) == angle.GetName().charAt(1)) {
            numAngle1 = 1;
            numAngle2 = 2;
        }m  
        if (triangle.getAngles()[1].GetName().charAt(1) == angle.GetName().charAt(1)) {
            numAngle1 = 0;
            numAngle2 = 2;
        }
        if (triangle.getAngles()[2].GetName().charAt(1) == angle.GetName().charAt(1)) {
            numAngle1 = 0;
            numAngle2 = 1;
        }

        if (angle.getValue() != -1 && triangle.getAngles()[numAngle1].getValue() != -1) {
            Angle a = triangle.getAngles()[numAngle2];
            way1 = exercise.getWayOfGiven1(new Given(angle, EAll.שווה, angle.getValue()));
            way2 = exercise.getWayOfGiven1(new Given(triangle.getAngles()[numAngle1], EAll.שווה, triangle.getAngles()[numAngle1].getValue()));
            way=new ArrayList<String>();
            Utils.addAllWay(way,way1);
            Utils.addAllWay(way,way2);
            way.add(sentence);
            if(exercise.setGivenValueOfAngle1(a, angle.getValue() - triangle.getAngles()[numAngle1].getValue(), way)) {
                bool = true;
                return bool;
            }




        }
        if (angle.getValue() != -1 && triangle.getAngles()[numAngle2].getValue() != -1) {
            Angle a = triangle.getAngles()[numAngle1];

            way1 = exercise.getWayOfGiven1(new Given(angle, EAll.שווה, angle.getValue()));
            way2 = exercise.getWayOfGiven1(new Given(triangle.getAngles()[numAngle2], EAll.שווה, triangle.getAngles()[numAngle2].getValue()));
            way=new ArrayList<String>();
            Utils.addAllWay(way, way1);
            Utils.addAllWay(way,way2);
            way.add(sentence);
            if(exercise.setGivenValueOfAngle1(a, angle.getValue() - triangle.getAngles()[numAngle2].getValue(), way)) {
                bool = true;
                return bool;
            }

        }
        if (triangle.getAngles()[numAngle2].getValue() != -1 && triangle.getAngles()[numAngle1].getValue() != -1) {


            way1 = exercise.getWayOfGiven1(new Given(triangle.getAngles()[numAngle1], EAll.שווה, triangle.getAngles()[numAngle1].getValue()));
            way2 = exercise.getWayOfGiven1(new Given(triangle.getAngles()[numAngle2], EAll.שווה, triangle.getAngles()[numAngle2].getValue()));
            way=new ArrayList<String>();
            Utils.addAllWay(way,way1);
            Utils.addAllWay(way,way2);
            way.add(sentence);

            if(exercise.setGivenValueOfAngle1(angle, triangle.getAngles()[numAngle1].getValue() + triangle.getAngles()[numAngle2].getValue(), way)) {
                bool = true;
                return bool;
            }
        }
        way1 = exercise.getWayOfGiven1(new Given(triangle.getAngles()[numAngle1], EAll.שווה, triangle.getAngles()[numAngle2]));
        if (way1 != null && angle.getValue() != -1) {
            way2 = exercise.getWayOfGiven1(new Given(angle, EAll.שווה, angle.getValue()));
            sentence +="\n"+ " כיון שהזויות שוות הערך מתחלק שתיהם ";
            way=new ArrayList<String>();
            Utils.addAllWay(way,way1);
            Utils.addAllWay(way,way2);
            way.add(sentence);
            if(exercise.setGivenValueOfAngle1(triangle.getAngles()[numAngle1], angle.getValue() / 2, way)) {
                bool = true;

            }
            if(exercise.setGivenValueOfAngle1(triangle.getAngles()[numAngle2], angle.getValue() / 2, way)) {
                bool = true;
            }

        }

return false;
    }
	//get exercise and sent to check whith exercise and triangle and angle
	
    @Override
    public boolean goOver(Exercise exercise, Context context) {
        this.context=context;
        boolean b = false;
        for (Angle angle : exercise.getAngles()) {
            for (Triangle triangle : exercise.getTriangles()) {
            boolean temp = check(exercise, new Item[]{triangle,angle});
            if (temp)
                b = true;
        }
        }
        return b;
    }

}
