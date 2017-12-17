package com.example.tia29.geometry.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.ParcelFileDescriptor;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.MyPoint;


import java.util.ArrayList;

/**
 * Created by Naomi on 11/12/2017.
 */
public class MyTriangleView extends View {
    private static final String TAG = "";
    private Context context = null;
    int counter = 0;
    char a = ' ';
    char b = ' ';
    char c = ' ';
    int x1 = -90;
    int y1 = -90;
    int x2 = -90;
    int y2 = -90;
    int x3 = -90;
    int y3 = -90;
    private  boolean flag=false;
    MyPoint p1 = new MyPoint(a + "", 0, 0);
    MyPoint p2 = new MyPoint(b + "", 0, 0);
    MyPoint p3 = new MyPoint(c + "", 0, 0);
    MyPoint tempP = null;
    private static ArrayList<MyPoint> existingPoints;
    IDrawDone drawDone = null;

    public MyTriangleView(Context context) {
        super(context);
        this.context = context;
    }

    public MyTriangleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static void setExistingPoints(ArrayList<MyPoint> existingPoints) {
        MyTriangleView.existingPoints = existingPoints;
    }


  @Override
  protected void onDraw(Canvas canvas) {
      Log.d(TAG, "onDraw");
      canvas.drawARGB(0,0,0,0);
      Paint p = new Paint();
      p.setStrokeWidth(2);
      p.setTextSize(40);
      Path path = new Path();
      p.setStyle(Paint.Style.STROKE);

      if (counter == 2) {
          if((Math.abs(x1 - x2) <= 20) && (Math.abs(y1 - y2) <= 20))
              return;
          Log.d(TAG, "line");
          canvas.drawText(getA() + "", x1 - 10, y1 - 5, p);
          canvas.drawText(getB() + "", x2 - 10, y2 - 5, p);
          canvas.drawLine(x1, y1, x2, y2, p);
      }
      if (counter >= 3) {
          Log.d(TAG, "tri");
          flag = true;
          p1.setX(x1 + getX());
          p1.setY(y1 + getY());

          p2.setX(x2 + getX());
          p2.setY(y2 + getY());

          p3.setX(x3 + getX());
          p3.setY(y3 + getY());

          counter++;
          Log.d("update tar","tri counter"+counter);

          x2 = (int) p2.getX() - (int) getX();
          y2 = (int) p2.getY() - (int) getY();
          x1 = (int) p1.getX() - (int) getX();
          y1 = (int) p1.getY() - (int) getY();
          x3 = (int) p3.getX() - (int) getX();
          y3 = (int) p3.getY() - (int) getY();

          if(!((Math.abs(x1 - x2) <= 20) && (Math.abs(y1 - y2) <= 20))&&!((Math.abs(x1 - x3) <= 20) &&(Math.abs(y1 - y3) <= 20))&&!((Math.abs(x2 - x3) <= 20) && (Math.abs(y2 - y3) <= 20))) {
              path.moveTo(x1, y1);
              path.lineTo(x2, y2);
              path.lineTo(x3, y3);
              path.lineTo(x1, y1);

              canvas.drawText(getA() + "", x1 - 10, y1 - 5, p);
              canvas.drawText(getB() + "", x2 - 10, y2 - 5, p);
              canvas.drawText(getC() + "", x3 - 10, y3 - 5, p);
              canvas.drawPath(path, p);
			  }

          //counter = 0;

      }
      if (flag&&counter==4) {
          setBackgroundColor(Color.TRANSPARENT);

          Log.d(TAG, "drawDone");
          drawDone.drawDone(p1, p2, p3);
          flag = false;
      } else {
          Log.d(TAG, "no need drawDone");
      }
      super.onDraw(canvas);
  }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (counter == 0) {
                    x1 = (int) event.getX();
                    y1 = (int) event.getY();
                    counter++;
            }


        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (counter) {

                case 1:
                    x2 = (int) event.getX();
                    y2 = (int) event.getY();

                    counter++;
                    invalidate();
                    break;
                case 2:
                    x3 = (int) event.getX();
                    y3 = (int) event.getY();
                    new Point(x2 + (int) getX(), y2 + (int) getY());
                    counter++;
                    invalidate();

                    break;
            }
        }
        return true;
    }


    public MyPoint[] getPoints() {
        return new MyPoint[]{p1, p2, p3};
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

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
        p3.setName(c + "");
    }

public void setEmptySpaces(char a, char b, char c){
    this.a=a;
    this.b=b;
    this.c=c;
}


    public void changePoint(MyPoint newPoint, int index) {
        switch (index) {
            case 0:
                p1 = newPoint;
         //       setA(p1.GetName().charAt(0));
                setEmptySpaces(' ',b,c);
                break;
            case 1:
                p2 = newPoint;
           //     setB(p2.GetName().charAt(0));
                setEmptySpaces(a,' ',c);
                break;
            case 2:
                p3 = newPoint;
          //      setC(p3.GetName().charAt(0));
                setEmptySpaces(a,b, ' ');
                break;
        }
    }

    public void setDrawDone(IDrawDone drawDone) {
        this.drawDone = drawDone;
    }
}
