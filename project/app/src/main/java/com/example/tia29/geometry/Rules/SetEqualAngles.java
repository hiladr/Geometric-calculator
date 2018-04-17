package com.example.tia29.geometry.Rules;

import android.content.Context;

import com.example.tia29.geometry.Entites.Angle;
import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.Given;
import com.example.tia29.geometry.Entites.Item;
import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.Utils;

import java.util.ArrayList;

public class SetEqualAngles implements MyRules {
    private Context context;
ArrayList<String>way=new ArrayList<String>();
    @Override
    public boolean check(Exercise exercise, Item[] items) {
        boolean wasChanged=false;
        if (items[0] instanceof Angle && items[1] instanceof Angle) {
            Angle a1 = (Angle) items[0];
            Angle a2 = (Angle) items[1];
         if (exercise.areAnglesIsTheSameAngle(a1, a2))
            wasChanged=result(exercise, new Item[]{a1,a2});

        }
        return wasChanged;
    }
    @Override
    public boolean result(Exercise exercise, Item[] items) {

        boolean wasChanged = false;
        if (items[0] instanceof Angle && items[1] instanceof Angle) {

            String sentence = "זווית שווה לעצמה";
            Angle a1 = (Angle) items[0];
            Angle a2 = (Angle) items[1];
            way.add(sentence);
            Given given = new Given(a1, EAll.שווה, a2);
            Given tempGiv = exercise.addGeneralGiven1(given, way);
            if (tempGiv.equals(given)) {
                wasChanged = true;
            }
        }
        return wasChanged;
    }
    @Override
    public boolean goOver(Exercise exercise, Context context) {

        this.context=context;
        boolean b=false;

        Angle a1,a2;
        ArrayList<Angle> angles;
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
