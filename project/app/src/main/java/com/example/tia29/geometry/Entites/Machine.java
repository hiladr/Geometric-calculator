package com.example.tia29.geometry.Entites;
import android.content.Context;
import android.graphics.Path;
import android.provider.MediaStore;
import android.util.Log;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.Rules.MyRules;
import com.example.tia29.geometry.UI.IProveDone;

import java.io.File;
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
    /*
     * This function start running the machine
     * @returns
     */
    public void runMachine() {
        init();
        engine();
    }

    /*
 * This function is the main recursive function
 * it goes over all rules and checks for each rule 
 * if it can be learned.
 * @returns
 */
    public void engine() {
        boolean learned = false;
    //learn rules
       for (MyRules myRule : myRules) {
            if (myRule.goOver(exercise, context)) 
                learned = true;
        }

        if (learned) {
            //check again if new rules can be learned
            engine();
            return;
        }
        //if no rule was learned, finished learning
        ArrayList<String> way = exercise.getWayOfGiven1(prove);
        if (way != null) {
            printTheProve(way);
            return;
        }
        //can't prove
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(context.getString(R.string.NoProve));
        iProofDone.proofDone(temp);
    }

    //send the prove back to the main activity
    public ArrayList<String> printTheProve(ArrayList<String> way) {
        iProofDone.proofDone(way);
        return way;
    }
    /*
 * This function inits the machine.
 * it creates a given and sets way for every given in exercise.
 * @returns
 */
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

    /*
  * This function takes care of reading the classes from rules package
  * @returns
  */
    public void reflection() {
        String pkg = "com.example.tia29.geometry.Rules";
        //get all rules
        String[] claases = getClassesOfPackage(pkg);
        for (int i = 0; i < claases.length; i++) {
            //create object for each rule
            try {
                Class<?> clazz = null;
                clazz = Class.forName(pkg + "." + claases[i]);
                Constructor<?> ctor = clazz.getConstructor();
                Object object = ctor.newInstance(new Object[]{});
                myRules.add((MyRules) object);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
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





