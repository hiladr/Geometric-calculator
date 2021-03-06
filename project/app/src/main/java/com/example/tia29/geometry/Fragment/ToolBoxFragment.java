package com.example.tia29.geometry.Fragment;


import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.MyPoint;
import com.example.tia29.geometry.Entites.Segment;
import com.example.tia29.geometry.Entites.Triangle;
import com.example.tia29.geometry.Enums.EEntityTypes;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.UI.MainActivity;
import com.example.tia29.geometry.UI.MyLineView;
import com.example.tia29.geometry.UI.MyTriangleView;
import com.example.tia29.geometry.UI.PointView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToolBoxFragment extends Fragment {
    View mView = null;
    ArrayList<MyPoint> mPointsOnScreen = null;
    MainActivity mainActivity = null;
    Exercise mExercise;
    int draggedItem;
    ImageView mImageSegment = null;
    ImageView mImageTriangle = null;
    MyTriangleView mLastAddedtri = null;
    MyLineView mLastAddedLine = null;

    public void setExercise(Exercise exercise) {
        this.mExercise = exercise;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public ToolBoxFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tool_box, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPointsOnScreen = new ArrayList<MyPoint>();
        MyLineView.setExistingPoints(mPointsOnScreen);
        MyTriangleView.setExistingPoints(mPointsOnScreen);

        this.mView = view;
        //find all items on the screen
        mImageSegment = (ImageView) view.findViewById(R.id.seg);
        mImageTriangle = (ImageView) view.findViewById(R.id.tri);
        mImageSegment.setTag("TAG");//must be for adding drag option
        mImageTriangle.setTag("TAG1");//must be for adding drag option
        mImageTriangle.setOnTouchListener(onTouchListener);
        mImageSegment.setOnTouchListener(onTouchListener);
        view.findViewById(R.id.toplinear).setOnDragListener(new MyDragListener());
        view.findViewById(R.id.bottomlinear).setOnDragListener(new MyDragListener());
    }

    //for the drag
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                startDrag(v);
                return true;
            }
            return false;
        }
    };

    //start a dragging event on a given view
    private void startDrag(View view) {
        draggedItem = view.getId();
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(data, //data to be dragged
                shadowBuilder, //drag shadow
                view, //local data about the drag and drop operation
                0 //no needed flags
        );
    }

    //called when done drawing the item
    public void drawDone(MyPoint[] oldPoints) {
        ArrayList<MyPoint> points = new ArrayList<MyPoint>();
        for(int i = 0; i < oldPoints.length ; i++) {
            points.add(oldPoints[i]);
        }

            for(int i = 1; i < points.size() ; i++) {
            for(int j = 0; j < i ; j++){
                if(points.get(i).equals(points.get(j))){
                    mExercise.freeLetter(points.get(i).GetName().charAt(0));
                    points.remove(i);
                }
            }
        }


            if(points.size() != oldPoints.length){
                for (MyPoint point : points) {
                    checkSamePoint(point, -1, EEntityTypes.angle);
                }
                return;
            }

        if (oldPoints.length == 3) {
            for (int i = 0; i < 3; i++) {
                checkSamePoint(oldPoints[i], i, EEntityTypes.triangle);
            }
        }

        if (oldPoints.length == 2) {

            for (int i = 0; i < 2; i++) {
                checkSamePoint(oldPoints[i], i, EEntityTypes.segment);
            }

        }

    }
	
	        //called when a segment was done drawing
    private void lineCheckPointsDone() {
       // Log.d("update tar", "lineCheckPointsDone");
        MyPoint myPoint[] = mLastAddedLine.getPoints();
        String segmentName = mExercise.onDragSegment(myPoint[0], myPoint[1]);
        Segment s = mExercise.GetSegmentByName(segmentName);
        mExercise.createNewSegmentsBySegment(s);
        mExercise.createNewAngles();
        mExercise.createNewTriangle();
        mainActivity.notifyAdapters();
        mLastAddedLine.invalidate();
    }
		
		//called when a triangle was done drawing
     private void triCheckPointsDone() {
        // Log.d("update tar", "triCheckPointsDone");
        //  mImageTriangle.setEnabled(false);
        MyPoint myPoint[] = mLastAddedtri.getPoints();
        String triNAme = mExercise.onDragTriangle(myPoint[0], myPoint[1], myPoint[2]);
        Triangle t = mExercise.getTriangleByName(triNAme);
        Segment[] segments = t.getSegments();

        mExercise.createNewSegmentsBySegment(segments[0]);
        mExercise.createNewSegmentsBySegment(segments[1]);
        mExercise.createNewSegmentsBySegment(segments[2]);
        mExercise.createNewAngles();
        mExercise.createNewTriangle();
        mainActivity.notifyAdapters();
        mLastAddedtri.invalidate();
    }

	
	 //check if there are points that connect
   public void checkSamePoint(final MyPoint p, final int i, final EEntityTypes etype) {
        MyPoint closePoint = findClosePoint(p);
        if (closePoint != null) {
            if (etype == EEntityTypes.triangle) {
                mLastAddedtri.changePoint(closePoint, i);//change points
            } else if (etype == EEntityTypes.segment) {
                mLastAddedLine.changePoint(closePoint, i);
            }
            mExercise.freeLetter(p.GetName().charAt(0));

        }
		        //for single points that are left from not drawn items
        else if(i == -1)
            mExercise.freeLetter(p.GetName().charAt(0));
		
        Log.d("update tar", "check last point");
        if (etype == EEntityTypes.segment && i == 1) {
            lineCheckPointsDone();
        } else if (etype == EEntityTypes.triangle && i == 2) {
            triCheckPointsDone();
        }
    }

    //check if there is a close point to this point
    public MyPoint findClosePoint(MyPoint point) {
        double x = point.getX();
        double y = point.getY();
        for (MyPoint p : mExercise.getPoints()) {
            if (Math.abs(p.getX() - x) <= 20 && Math.abs(p.getY() - y) <= 20 && p.getId() != point.getId()) {
                return p;
            }

        }
        return null;
    }
	
	class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            switch (event.getAction()) {
                //drag shadow has been released,the drag point is within the bounding box of the View
                case DragEvent.ACTION_DROP:
                    // if the mView is the bottomlinear, we accept the drag item
                    if (v == mView.findViewById(R.id.bottomlinear)) {
                        RelativeLayout containView = (RelativeLayout) v;

                        //if the dragged item is a triangle
                        if (draggedItem == R.id.tri) {

                            String triName = mExercise.getNewTriangleName();
                            View addedView = View.inflate(mainActivity, R.layout.triangle_image, null);
                            containView.addView(addedView);
                            mLastAddedtri = (MyTriangleView) addedView.findViewById(R.id.view);
                            mLastAddedtri.setDrawDone(mainActivity);
                            mLastAddedtri.setA(triName.charAt(0));
                            mLastAddedtri.setB(triName.charAt(1));
                            mLastAddedtri.setC(triName.charAt(2));
                            mLastAddedtri.invalidate();


                        }

                        //if the dragged item is a segment
                        if (draggedItem == R.id.seg) {
                            String segName = mExercise.getNewSegmentName();
                            View addedView = View.inflate(mainActivity, R.layout.line_image, null);
                            containView.addView(addedView);
                            mLastAddedLine = (MyLineView) addedView.findViewById(R.id.view);
                            mLastAddedLine.setA(segName.charAt(0));
                            mLastAddedLine.setB(segName.charAt(1));
                            mLastAddedLine.invalidate();
                            mLastAddedLine.setDrawDone(mainActivity);


                        }

                    } else {
                        View view = (View) event.getLocalState();
                        view.setVisibility(View.VISIBLE);
                        Context context = mainActivity.getApplicationContext();
                        Toast.makeText(context, "You can't drop the image here",
                                Toast.LENGTH_LONG).show();
                        break;
                    }
                    break;

            }
            return true;
        }
    }

	
//add a point event
   public void addPoint(char a, float x, float y) {
        RelativeLayout containsView = (RelativeLayout) mView.findViewById(R.id.bottomlinear);
        View addedView = View.inflate(mainActivity, R.layout.mypoint_image, null);
        PointView yView = (PointView) addedView.findViewById(R.id.view);
        yView.setA(a);
        yView.setX(x);
        yView.setY(y);
        containsView.addView(addedView);
        yView.invalidate();

    }


    
}

