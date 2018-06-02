
package com.example.tia29.geometry.Rules;

import android.content.Context;

import com.example.tia29.geometry.Entites.Angle;
import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.Given;
import com.example.tia29.geometry.Entites.Item;
import com.example.tia29.geometry.Entites.Segment;
import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.Utils;

import java.util.ArrayList;


public class ShniGdalimShavim implements MyRules{
ArrayList<String> way=new ArrayList<String>();
    Exercise exercise;
    Context context;


    @Override
    public boolean check(Exercise exercise, Item[] items) {
        String sentence=context.getString(R.string.shneiGdalim);
        boolean flag=false;
        Given g1;
        Given g2;
        Given g3;
        if(items[0]instanceof Segment)
        {

            Segment s1=(Segment) items[0];
            Segment s2=(Segment) items[1];
            Segment s3=(Segment) items[2];
            g1=exercise.getGivenInExercise(new Given (s1, EAll.שווה,s2));
            g2=exercise.getGivenInExercise(new Given (s1,EAll.שווה,s3));
            g3=exercise.getGivenInExercise(new Given(s2, EAll.שווה,s3));
            if(g1!=null&&g2!=null )
            {
                Utils.addAllWay(way, g1.getWay1());
                Utils.addAllWay(way, g2.getWay1());
              way.add(sentence);
                if(result(exercise,new Item[]{s2,s3}))
                   flag=true;
            }
            if(g2!=null &&g3!=null)
            {
                way=new ArrayList<String>();
                Utils.addAllWay(way, g2.getWay1());
                Utils.addAllWay(way, g3.getWay1());
                way.add(sentence);
                if(result(exercise, new Item[]{s1, s2}))
                    flag=true;
            }
            if(g1!=null&&g3!=null)
            {
                way=new ArrayList<String>();
                Utils.addAllWay(way, g1.getWay1());
                Utils.addAllWay(way, g3.getWay1());
                way.add(sentence);
                if(result(exercise, new Item[]{s1, s3}))
                    flag=true;
            }

        }
        if(items[0]instanceof Angle)
        {
            Angle a1=(Angle) items[0];
            Angle a2=(Angle) items[1];
            Angle a3=(Angle) items[2];
            g1=exercise.getGivenInExercise(new Given (a1,EAll.שווה,a2));
            g2=exercise.getGivenInExercise(new Given (a1,EAll.שווה,a3));
            g3=exercise.getGivenInExercise(new Given(a2, EAll.שווה,a3));
            if(g1!=null&&g2!=null )
            {
                way=new ArrayList<String>();
                Utils.addAllWay(way, g1.getWay1());
                Utils.addAllWay(way, g2.getWay1());
                way.add(sentence);
                if(result(exercise, new Item[]{a2, a3}))
                    flag=true;

            }
            if(g2!=null &&g3!=null)
            {
                way=new ArrayList<String>();
                Utils.addAllWay(way, g2.getWay1());
                Utils.addAllWay(way, g3.getWay1());
                way.add(sentence);
                if(result(exercise, new Item[]{a1, a2}))
                    flag=true;

            }
            if(g1!=null&&g3!=null)
            {
                way=new ArrayList<String>();
                Utils.addAllWay(way, g1.getWay1());
                Utils.addAllWay(way, g3.getWay1());
                way.add(sentence);
                if(result(exercise, new Item[]{a1, a3}))
                    flag=true;

            }
        }
        return flag;
    }

    @Override
    public boolean result(Exercise exercise, Item[] items) {
        if(items[0] instanceof Segment)
        {
            Segment s1= (Segment) items[0];
            Segment s2= (Segment) items[1];
            return exercise.setGivenThatSegmentsEqual1(s1,s2,way );

        }
        if(items[0] instanceof Angle)
        {
            Angle a1= (Angle) items[0];
            Angle a2= (Angle) items[1];
            return exercise.setGivenThatAnglesEqual1(a1,a2 ,way);

        }
        return false;
    }

    @Override
    public boolean goOver(Exercise exercise, Context context) {

        this.context=context;
        boolean flag=false;
        ArrayList< Segment> listSegment=exercise.getSegments();
        int size=listSegment.size();
        //לעבור על הקטעים
        for(int i=0;i< size;i++)
        {
            if(i+1<size)
            {
                for(int j=i+1;j< size;j++)
                {  if(j+1<size)
                {
                    for (int l=j+1;l<size;l++)
                    {
                        if( check(exercise,new Item[]{listSegment.get(i),listSegment.get(j),listSegment.get(l)}))
                        {
                            flag=true;
                        }
                    }
                }
                }
            }
        }
        ArrayList< Angle> listAngles=exercise.getAngles();
        int sizeA=listAngles.size();
   //לעבור על כל הזויות
        for(int i=0;i< sizeA;i++)
        {
            if(i+1<size)
            {
                for(int j=i+1;j< sizeA;j++)
                {  if(j+1<size)
                {
                    for (int l=j+1;l<sizeA;l++)
                    {
                        if( check(exercise,new Item[]{listAngles.get(i),listAngles.get(j),listAngles.get(l)}))
                        {
                            flag=true;
                        }
                    }
                }
                }
            }
        }


        return flag;
    }


}
