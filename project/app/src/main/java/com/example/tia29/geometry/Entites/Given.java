package com.example.tia29.geometry.Entites;

import android.content.Context;

import com.example.tia29.geometry.Enums.EAll;
//import com.example.tia29.geometry.R;
import com.example.tia29.geometry.Utils;

import java.util.ArrayList;

public class Given {
    int id ;
    private  Item item1;
    private Enum  operand;
    private    Item  item2;
    private    Item  item3;
    private double value=-1;

   private ArrayList<String> ways=new ArrayList<String>();

    private   static  int idIndex;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Given)) return false;

        Given given = (Given) o;

        if (id != given.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
    public Given(Item item1, Enum operand, Item item2) {
        this.item1 = item1;
        this.operand = operand;
        this.item2 = item2;

        id=idIndex++;
    }
    public Given(Item item1, Enum operand, Item item2,Item item3) {
        this.item1 = item1;
        this.operand = operand;
        this.item2 = item2;
        this.item3=item3;

        id=idIndex++;
    }
    public Given(Item item1, Enum operand,double value) {
        this.item1 = item1;
        this.operand = operand;
        this.value=value;
        id=idIndex++;
    }
    public Given(Item item1, Enum operand) {

        this.item1 = item1;
        this.operand = operand;
        id=idIndex++;
    }
    @Override
    public String toString()
    {
        if(value!=-1){
            return  (item1+ "="+value);
        }
        if(item3!=null)
            return  (item1+ " "+operand.name()+" "+item2+"="+item3);

        if(operand== EAll.ישר_זוית)
            return  (item1+ " "+operand.name()+" "+item2+"=90");
        if(value>0)
        {
            return  (item1+ " "+operand.name()+"= "+value);
        }
        if(item2==null) {
            return (item1 + " " + operand.name());
        }
if(operand== EAll.שווה)
{
    return  (item1+ " = "+item2);
}
        return  (item1+ " "+operand.name()+" "+item2);
    }
    public Item getItem1() {
        return item1;
    }
    public void setItem1(Item item1) {
        this.item1 = item1;
    }
    public Enum getOperand() {
        return operand;
    }
    public void setOperand(Enum operand) {
        this.operand = operand;
    }
    public Item getItem2() {
        return item2;
    }
    public void setItem2(Item item2) {
        this.item2 = item2;
    }
    public Item getItem3() {
        return item3;
    }
    public void setItem3(Item item3) {
        this.item3 = item3;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public static int getIdIndex() {
        return idIndex;
    }
    public static void setIdIndex(int idIndex) {
        Given.idIndex = idIndex;
    }
   public String getWay() {

       return "    ";
    }
    public ArrayList<String> getWay1() {
        return ways;
    }
    public void setWay(ArrayList  <String> way) {
        boolean b=false;

        if(ways!=null){
        for(int i=0; i<way.size(); i++) {
            b = false;
            String tempway = way.get(i);
           // b=isSentenceInWay(ways,tempway);
         for (int j = 0; j < ways.size(); j++) {
                if (tempway.equals(ways.get(j))) {
                    b = true;
                }
            }
            if (b &&tempway!=null&& tempway.contains('-'+"")   &&  i>0
                    && ways.size()>0
                    &&(way.get(i-1).equals(ways.get(ways.size()-1)))
              && i<way.size()-1

                 &&!isSentenceInWay(ways,way.get(i+1) )
                    )
            {
                //add the sentence
                b=false;
            }

            if( tempway!=null && tempway.contains("=>") &&    ways.size()>0 &&!(ways.get(ways.size()-1)).contains("=>"))
            {
                b=false;
            }


        if(!b)
            {
                ways.add(tempway);
         }
        }
        }

    }
    public boolean areSegmentsOnSameSegment(Segment s1, Segment s2) {
        boolean b = false;
        MyPoint p1 = s1.getPoint1();
        MyPoint p2 = s1.getPoint2();
        MyPoint p3 = s2.getPoint1();
        MyPoint p4 = s2.getPoint2();
        float a1 = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        float b1 = p1.getY() - a1 * p1.getX();
        float a2 = (p3.getY() - p4.getY()) / (p3.getX() - p4.getX());
        float b2 = p3.getY() - a2 * p3.getX();
        if ((a1 == a2) && (b2 == b1))

            b = true;
        return b;
    }
    public boolean isSentenceInWay(ArrayList<String>way,String s){
        boolean b=false;
        for(String way1:way)
        {
            if(way1.equals(s))
                b=true;
            return b;
        }
        return b;
    }
    public void setNewWay(ArrayList<String> way) {
        ways=new ArrayList<String>();
        Utils.addAllWay(ways,way);
    }
    public int getLengthOfGiven(){
        int count=0;
        for(int j=0; j<ways.size(); j++){
           count+=ways.get(j).length();
        }
        return count;
    }

}
