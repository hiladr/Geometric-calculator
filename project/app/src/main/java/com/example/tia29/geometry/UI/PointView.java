package com.example.tia29.geometry.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Naomi on 02/12/2017.
 */
public class PointView extends View {
    public PointView(Context context) {
        super(context);
    }

    public PointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    Context context;
    float x;
    float y;
    char a;




    public void setParameters(float x, float y, char a ){
        this.x=x;
        this.y=y;
        this.a=a;
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setStrokeWidth(2);
        p.setStyle(Paint.Style.STROKE);
        p.setTextSize(60);
        canvas.drawText(a+ "", x+ 3, y, p);
    }


    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public char getA() {
        return a;
    }

    public void setA(char a) {
        this.a = a;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }



    public void setContext(Context context) {
        this.context = context;
    }
}
