package com.example.tia29.geometry.UI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.MyPoint;
import com.example.tia29.geometry.Fragment.ToolBoxFragment;
import com.example.tia29.geometry.R;


public class MainActivity extends Activity implements IDrawDone {
    Exercise mExercise;
    ToolBoxFragment mToolBoxFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();
    }


    //new problem-new Exercise
    public void newProb(View view) {
        start();
    }

    //starts a new Exercise
    public void start() {
        mExercise = new Exercise(this);
        mExercise.setMainActivity(this);
		mToolBoxFragment = new ToolBoxFragment();
        getFragmentManager().beginTransaction().replace(R.id.toolBoxfragment, mToolBoxFragment, "f1").addToBackStack("f1").commit();
        mToolBoxFragment.setExercise(mExercise);
		mToolBoxFragment.setMainActivity(this);

    }
	
	    //wakes an event to add a point to the screen
    public void addPoint(char a, float x, float y) {
        mToolBoxFragment.addPoint(a, x, y);
    }

	
	@Override
    public void drawDone(MyPoint    [] points) {
        mToolBoxFragment.drawDone(points);
    }
		



}
