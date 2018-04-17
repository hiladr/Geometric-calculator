package com.example.tia29.geometry.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.UI.MainActivity;
import com.example.tia29.geometry.UI.ProveAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProveFragment extends Fragment {
    View view = null;
    Exercise exercise = null;
    MainActivity mainActivity = null;
    ProveAdapter proveAdapter;

    public ProveFragment() {
        // Required empty public constructor
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prove, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
    }

    //print the prove
    public void setProve(ArrayList<String> s) {
        proveAdapter = new ProveAdapter(mainActivity.getLayoutInflater(), exercise);
        proveAdapter.setProofArrayList(s);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(proveAdapter);

    }

}
