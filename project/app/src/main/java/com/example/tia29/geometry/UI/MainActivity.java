package com.example.tia29.geometry.UI;

import com.example.tia29.geometry.R;

import java.util.ArrayList;


public class MainActivity extends Activity implements IDrawDone {
   


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

    }
	
	@Override
    public void drawDone(MyPoint[] points){
		
	}



}
