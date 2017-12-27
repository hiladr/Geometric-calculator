package com.example.tia29.geometry.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.Given;
import com.example.tia29.geometry.R;

import java.util.ArrayList;



//for the given list
public class GivenAdapter extends BaseAdapter {

    ArrayList<Given> givenArrayList = null;
    LayoutInflater inflate = null;
    Exercise exercise = null;

    public GivenAdapter(LayoutInflater inflate, Exercise exercise) {
        this.givenArrayList = exercise.getGivenList();
        this.inflate = inflate;
        this.exercise = exercise;
    }

    @Override
    public int getCount() {
        return givenArrayList.size();
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

        if (convertView == null) {
            convertView = inflate.inflate(R.layout.given, null);

        }
        TextView textView;
        textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(givenArrayList.get(position).toString());
        ImageView imageView = (ImageView) convertView.findViewById(R.id.delete);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                exercise.deleteGiven(position);
                notifyDataSetChanged();
            }
        });


        return convertView;
    }
}


















