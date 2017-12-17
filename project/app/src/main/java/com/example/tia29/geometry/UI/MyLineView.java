package com.example.tia29.geometry.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.MyPoint;

import java.util.ArrayList;

/**
 * Created by Naomi on 06/12/2017.
 */


public class MyLineView extends View {
    int x1, y1, x2, y2;
    char a = ' ';
    char b = ' ';
    int counter = 0;
    boolean enabled = true;
    private Boolean flag = false;
    IDrawDone drawDone = null;
    MyPoint p1 = new MyPoint(a + "", 0, 0);
    MyPoint p2 = new MyPoint(b + "", 0, 0);
    Context context = null;
    private static ArrayList<MyPoint> existingPoints;

    public static void setExistingPoints(ArrayList<MyPoint> existingPoints) {
        MyLineView.existingPoints = existingPoints;
    }


    public MyLineView(Context context) {
        super(context);
        this.context = context;
    }

    public MyLineView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        counter++;
        //set points in relation to view
        p1.setX(x1 + getX());
        p1.setY(y1 + getY());

        p2.setX(x2 + getX());
        p2.setY(y2 + getY());


        x2 = (int) p2.getX() - (int) getX();
        y2 = (int) p2.getY() - (int) getY();
        x1 = (int) p1.getX() - (int) getX();
        y1 = (int) p1.getY() - (int) getY();

        Paint p = new Paint();
        p.setStrokeWidth(2);
        p.setTextSize(40);
        p.setStyle(Paint.Style.STROKE);
		
		//draw line and letters
       if (counter >= 3) {
            if(!flag) {
				if (x1 != x2 || y1 != y2) {
                canvas.drawText(getA() + "", x1 + 5, y1, p);
                canvas.drawText(getB() + "", x2 + 5, y2, p);
                if (canvas != null) {
                    canvas.drawLine(x1, y1, x2, y2, p);
                }
            }
			}

            else  {
                drawDone.drawDone(p1, p2);
                enabled = false;
                setBackgroundColor(Color.TRANSPARENT);
                flag = false;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    //first point
        if (event.getAction() == MotionEvent.ACTION_DOWN && enabled) {

            x1 = (int) event.getX();
            y1 = (int) event.getY();
            invalidate();

        }
        //second point
        if (event.getAction() == MotionEvent.ACTION_UP && enabled) {
            flag = true;
            x2 = (int) event.getX();
            y2 = (int) event.getY();

            invalidate();
        }


        return true;
    }

    public char getA() {
        return a;
    }

    public void setA(char a) {
        this.a = a;
        p1.setName(a + "");
    }

    public char getB() {
        return b;
    }

    public void setB(char b) {
        this.b = b;
        p2.setName(b + "");
    }

    public MyPoint[] getPoints() {
        return new MyPoint[]{p1, p2};
    }

    public void setEmptySpaces(char a, char b) {
        this.a = a;
        this.b = b;

    }

    public void setDrawDone(IDrawDone drawDone) {
        this.drawDone = drawDone;
    }

    public void changePoint(MyPoint newPoint, int index) {
        switch (index) {
            case 0:
                p1 = newPoint;
                setEmptySpaces(' ', b);
                break;
            case 1:
                p2 = newPoint;
                setEmptySpaces(a, ' ');
                break;

        }
    }
}
