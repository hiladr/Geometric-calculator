package com.example.tia29.geometry.Entites;
import android.content.Context;
import android.util.Log;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.Rules.MyRules;
import com.example.tia29.geometry.UI.IProveDone;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;

import dalvik.system.DexFile;

/**
 * Created by Naomi on 26/02/2018.
 */
public class Machine {
    IProveDone iProofDone = null;
    Exercise exercise;
    private Given prove;
    Context context;

    ArrayList<MyRules> myRules;

    public Machine(Exercise exercise, IProveDone iProofDone, Context context) {
        this.context = context;
        this.iProofDone = iProofDone;
        this.prove = exercise.getProve();
        this.exercise = exercise;
        myRules = new ArrayList<MyRules>();
        reflection();

    }

//start running the machine
    public void runMachine() {
        init();
        engine();
    }

//the main recursive function
    public void engine() {
        boolean b = false;

        for (MyRules myRule : myRules) {
            if (myRule.goOver(exercise, context)) {
                b = true;
               // Log.d(myRules1.toString(), "true");
            }
        }

        if (b) {
            engine();
            return;
        }

        ArrayList<String> way = exercise.getWayOfGiven1(prove);
        if (way != null) {
            printTheProve(way);
            return;
        }
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(context.getString(R.string.NoProve));
        iProofDone.proofDone(temp);
    }

//send the prove back to the main activity
    public ArrayList<String> printTheProve(ArrayList<String> way) {
        iProofDone.proofDone(way);
        return way;
    }

//init the machine
    public void init() {
        Segment s1;
        Angle a1;
        ArrayList<Given> givenList = exercise.getGivenList();
        int size = givenList.size();
        Given given;

//adds all givens entered by user to givenList and sets way of each given
       for (int i = 0; i < size; i++) {
            given = givenList.get(i);
            String s = given.toString() + "נתון";
            ArrayList<String> ss = new ArrayList<String>();
            ss.add(s);
            given.setWay(ss);
            }

        }

    }
	
	//gets classes from package
    private String[] getClassesOfPackage(String packageName) {
        ArrayList<String> classes = new ArrayList<String>();
        try {
            String packageCodePath = context.getPackageCodePath();
            DexFile df = new DexFile(packageCodePath);
            for (Enumeration<String> iter = df.entries(); iter.hasMoreElements(); ) {
                String className = iter.nextElement();
                if (className.contains(packageName)) {
                    classes.add(className.substring(className.lastIndexOf(".") + 1, className.length()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toStringArray(classes);
    }
//makes an arraylist to an array
    private String[] toStringArray(ArrayList<String> classes) {
        String[] array = new String[classes.size()];
        for (int i = 0; i < classes.size(); i++) {
            array[i] = classes.get(i);
        }
        return array;
    }


}





