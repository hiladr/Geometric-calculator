package com.example.tia29.geometry.UI;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.R;

import java.util.ArrayList;


public class ProveAdapter extends BaseAdapter {
    ArrayList<String> ProofArrayList =null;
    LayoutInflater inflate=null;

    Exercise exercise=null;
    public ProveAdapter(LayoutInflater inflate, Exercise exercise) {
        this.inflate=inflate;
        this.exercise=exercise;
    }

    public void setProofArrayList(ArrayList<String> proofArrayList) {
        ProofArrayList = proofArrayList;
    }

    @Override
    public int getCount() {
        return ProofArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView=inflate.inflate(R.layout.proof_layout,null);

        }
        TextView textViewValue=null;
        TextView textViewKey=null;
        textViewKey=(TextView)convertView.findViewById(R.id.key);
        textViewValue=(TextView)convertView.findViewById(R.id.value);

        String temp=ProofArrayList.get(position).toString();

        textViewValue.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewKey.setTextColor(Color.BLACK);
        textViewValue.setTextColor(Color.BLACK);
        textViewValue.setTextSize(15);


        if(temp.contains(convertView.getContext().getString(R.string.thereFor)))
        {
            textViewValue.setTextColor(Color.RED);
            textViewKey.setText("");
            textViewValue.setText(convertView.getContext().getString(R.string.thereFor));
        }
        else {
            if (temp.contains('-' + "")) {

                textViewValue.setTextColor(Color.argb(255, 143, 163, 79));
                textViewKey.setTextColor(Color.argb(255, 143, 163, 79));
                textViewKey.setText(convertView.getContext().getString(R.string.sentence));
                textViewValue.setText(temp);

            }

            else
            if (temp.contains(convertView.getContext().getString(R.string.given))) {
                textViewValue.setTextColor(Color.argb(255, 118, 117, 115));
                textViewKey.setTextColor(Color.argb(255, 118, 117, 115));

                textViewKey.setText(convertView.getContext().getString(R.string.given));
                String s=temp;
                s=s.substring(0,s.length()-4);
                textViewValue.setText(s);
            }
            else {
                if (temp.contains(convertView.getContext().getString(R.string.NoProve)))
                    textViewValue.setTextColor(Color.RED);
                else {

                        textViewValue.setText(temp);
                           textViewKey.setText("");
                }
                if (position == ProofArrayList.size() - 1) {
                    textViewValue.setTextColor(Color.BLUE);
                    textViewKey.setTextColor(Color.BLUE);
                    textViewValue.setText(temp);

                    textViewKey.setText(convertView.getContext().getString(R.string.mashal));
                }
            }
        }

        return convertView;
    }

}
