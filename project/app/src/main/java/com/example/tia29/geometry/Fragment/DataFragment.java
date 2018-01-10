package com.example.tia29.geometry.Fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.UI.GivenAdapter;
import com.example.tia29.geometry.UI.MainActivity;


public class DataFragment extends Fragment {

    ListView mListGiven;
    Exercise mExercise = null;
   MainActivity mainActivity = null;

    public DataFragment() {
        // Required empty public constructor
    }

    public void setExercise(Exercise exercise) {
        this.mExercise = exercise;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListGiven = (ListView) view.findViewById(R.id.givenList);
    }
//refresh the list of givens
    public void notifyData() {
        GivenAdapter givenArrayAdapter;
        givenArrayAdapter = new GivenAdapter(mainActivity.getLayoutInflater(), mExercise);
        mListGiven.setAdapter(givenArrayAdapter);
    }

}
