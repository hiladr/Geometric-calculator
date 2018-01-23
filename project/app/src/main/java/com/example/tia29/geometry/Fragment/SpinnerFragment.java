package com.example.tia29.geometry.Fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tia29.geometry.Dynamics.ParentObject;
import com.example.tia29.geometry.Dynamics.SubObject;
import com.example.tia29.geometry.Entites.Angle;
import com.example.tia29.geometry.Entites.Exercise;
import com.example.tia29.geometry.Entites.Given;
import com.example.tia29.geometry.Entites.Item;
import com.example.tia29.geometry.Entites.Segment;
import com.example.tia29.geometry.Enums.EAll;
import com.example.tia29.geometry.Enums.EAngle;
import com.example.tia29.geometry.Enums.ESegment;
import com.example.tia29.geometry.Enums.ETriangle;
import com.example.tia29.geometry.R;
import com.example.tia29.geometry.UI.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpinnerFragment extends Fragment {
    MainActivity mainActivity = null;
    String firstObject=null;

    Exercise mExercise = null;
    Button mAddGivenButton = null;
    Button mProveButton = null;
    Spinner mGivenObject1;
    Spinner mGivenObject2;
    Spinner mGivenObject3;
    Spinner mGivenOperand;
    EditText mEditTextGiven;
    Spinner mProveObject1;
    Spinner mProveObject2;
    Spinner mProveOperand;
    Button startProving;
    ArrayAdapter<String> mAdapterNames;
    ArrayAdapter<String> mAdapterNames2;
    ArrayAdapter<String> mAdapterNames3;
    ArrayAdapter<Enum> mAdapterOperand;
    Enum[] operands;
    ArrayList<String> elementsNames1;
    LinearLayout givens;
    LinearLayout proves;

    public SpinnerFragment() {
    }

    public void setExercise(Exercise exercise) {
        this.mExercise = exercise;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spinner, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
initSpinner();
        //מציאת הפרטים במסך
        mGivenObject1 = (Spinner) view.findViewById(R.id.givenObject1);
        mGivenObject2 = (Spinner) view.findViewById(R.id.givenObject2);
        mGivenObject3 = (Spinner) view.findViewById(R.id.givenObject3);
        mGivenOperand = (Spinner) view.findViewById(R.id.givenOperand);
        mEditTextGiven = (EditText) view.findViewById(R.id.myText);
        mAddGivenButton = (Button) view.findViewById(R.id.addGiven);
        mProveButton = (Button) view.findViewById(R.id.prove);
startProving= (Button) view.findViewById(R.id.startProving);
        startProving.setOnClickListener(showProve);
        mProveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prove(v);
            }
        });
        mAddGivenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGiven();
            }
        });
        mProveObject1 = (Spinner) view.findViewById(R.id.proveObject1);
        mProveObject2 = (Spinner) view.findViewById(R.id.proveObject2);
        mProveOperand = (Spinner) view.findViewById(R.id.proveOperand);
        mGivenObject1.setAdapter(mAdapterNames);
        mProveObject1.setAdapter(mAdapterNames);
           givens= (LinearLayout) view.findViewById(R.id.givenSppinners);
        proves= (LinearLayout) view.findViewById(R.id.proveSppinners);
        proves.setVisibility(View.INVISIBLE);
        mGivenOperand.setAdapter(mAdapterOperand);
        mProveOperand.setAdapter(mAdapterOperand);
        mGivenObject1.setOnItemSelectedListener(onItemSelectedListenerObject);
        mProveObject1.setOnItemSelectedListener(onItemSelectedListenerObject);

        mGivenOperand.setOnItemSelectedListener(onItemSelectedListenerOperand);
        mProveOperand.setOnItemSelectedListener(onItemSelectedListenerOperand);


    }

    AdapterView.OnItemSelectedListener onItemSelectedListenerOperand = new AdapterView.OnItemSelectedListener() {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


           List<ParentObject> jsonList = read();
            mEditTextGiven.setText("0");

            ArrayList<String> list = new ArrayList<String>();
            ArrayList<String> list1 = new ArrayList<String>();
            ArrayList<String> list2 = new ArrayList<String>();
            String s = ((TextView) view).getText().toString();
            if (jsonList != null) {

                ArrayList<SubObject> subObjects = null;
                for (ParentObject parentObject : jsonList) {
                    if (parentObject.getName().equals(firstObject)) {
                        subObjects = parentObject.getSubObjects();
                        break;
                    }
                }
                ArrayList<String> values = null;
                for (SubObject subObject : subObjects) {
                    if (subObject.getName().equals(s)) {
                        values = subObject.getValues();
                        break;
                    }
                }
                list = null;
                list1 = null;
                list2 = null;
                mEditTextGiven.setVisibility(View.INVISIBLE);
                mProveObject2.setVisibility(View.INVISIBLE);
                mGivenObject2.setVisibility(View.INVISIBLE);
                mGivenObject3.setVisibility(View.GONE);
                if (values != null) {
                    for (String value : values) {

                        if (value.equals("segment")) {
                            if (list == null)
                                list = mExercise.getSegmentsNames();
                            else if (list1 == null)
                                list1 = mExercise.getSegmentsNames();
                            else
                                list2 = mExercise.getSegmentsNames();
                        } else if (value.equals("angle")) {
                            if (list == null)
                                list = mExercise.getAnglesNames();
                            else if (list1 == null)
                                list1 = mExercise.getAnglesNames();
                            else
                                list2 = mExercise.getAnglesNames();
                        } else if (value.equals("triangle")) {
                            if (list == null)
                                list = mExercise.getTrianglesNames();
                            else if (list1 == null)
                                list1 = mExercise.getTrianglesNames();
                            else
                                list2 = mExercise.getTrianglesNames();
                        } else if (value.equals("value")) {
                            mEditTextGiven.setVisibility(View.VISIBLE);
                        }

                    }
                    if (list != null) {
                        list.add(0,"בחר");
                        mAdapterNames2 = new ArrayAdapter<String>(mainActivity, R.layout.string_layout, list);
                        mGivenObject2.setAdapter(mAdapterNames2);
                        mGivenObject2.setVisibility(View.VISIBLE);
                        mProveObject2.setAdapter(mAdapterNames2);
                        mProveObject2.setVisibility(View.VISIBLE);
                    }
                    if (list1 != null) {
                        list1.add(0,"בחר");
                        mAdapterNames3 = new ArrayAdapter<String>(mainActivity, R.layout.string_layout, list1);
                        mGivenObject3.setAdapter(mAdapterNames3);
                        mGivenObject3.setVisibility(View.VISIBLE);
                    }


                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    AdapterView.OnItemSelectedListener onItemSelectedListenerObject = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (view != null) {
                String s = ((TextView) view).getText().toString();

                switch (s.charAt(0)) {
                    //אם הוא משולש
                    case '^':
                        firstObject = "triangle";
                        operands = ETriangle.values();
                        break;
                    //אם הוא זוית
                    case '<':
                        firstObject = "angle";
                        operands = EAngle.values();
                        break;
                    //אם הוא קו
                    case '|':
                        firstObject = "segment";
                        operands = ESegment.values();
                        break;
                }


                mAdapterOperand = new ArrayAdapter<Enum>(mainActivity, R.layout.string_layout, operands);
                mGivenOperand.setAdapter(mAdapterOperand);
                mProveOperand.setAdapter(mAdapterOperand);


            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    //add a given
    public void addGiven() {
        int value = 0;String i1=null,i2=null,i3=null;
        if( mGivenObject1.getSelectedItem()!=null){
         i1 = mGivenObject1.getSelectedItem().toString();}
        if( mGivenObject2.getSelectedItem()!=null){
         i2 = mGivenObject2.getSelectedItem().toString();}
        if( mGivenObject3.getSelectedItem()!=null){
         i3 = mGivenObject3.getSelectedItem().toString();}

        Item item1 = mExercise.getElementByName(i1);
        Item item2 = mExercise.getElementByName(i2);
        Item item3 = mExercise.getElementByName(i3);

        if (mEditTextGiven.getText().toString() != "") {
            value = Integer.parseInt(mEditTextGiven.getText().toString());
        }
        Enum e = EAll.valueOf(mGivenOperand.getSelectedItem().toString());
        Given given = null;
        if (value != 0) {
            //given = new Given(item1, e, value);
            if (item1 instanceof Angle && value > 0 && value < 180) {
                given = new Given(item1, e, value);
                ((Angle) (item1)).setValue(value);
            }
            if (item1 instanceof Segment && value > 0) {
                given = new Given(item1, e, value);

                ((Segment) (item1)).setValue(value);
            }
        } else {
            if (item1 != null && item2 != null && item3 != null) {
                given = new Given(item1, e, item2, item3);
            } else {
                if (item1 != null && item2 != null) {
                    given = new Given(item1, e, item2);
                } else {
                    if (item2 == null) {
                        given = new Given(item1, e);
                    } else
                        given = new Given(item1, e, Integer.parseInt(mEditTextGiven.getText().toString()));
                }
            }

        }
if(given!=null){
        mExercise.addGiven(given);
        mainActivity.notifyData();
        mEditTextGiven.setText("0");
        }
    }

    //התחל את תהליך ההוכחה
    public void prove(View view) {
        int value = -1;
        Item item1 = mExercise.getElementByName(mProveObject1.getSelectedItem().toString());
        Item item2 = mExercise.getElementByName(mProveObject2.getSelectedItem().toString());
        Enum e = EAll.valueOf(mProveOperand.getSelectedItem().toString());


        Given given;

        given = new Given(item1, e, item2);
        mExercise.setProve(given);
        mainActivity.startProving();

    }
//sets the spinners after ading data
    public void notifyAdapters() {
        elementsNames1 = mExercise.GetElementsNames();
        mAdapterNames = new ArrayAdapter<String>(mainActivity, R.layout.string_layout, elementsNames1);
        mGivenObject1.setAdapter(mAdapterNames);
        mProveObject1.setAdapter(mAdapterNames);
    }
//reads from the json file
    public List<ParentObject> read() {
        List<ParentObject> list = null;
        try {
            StringBuilder buf = new StringBuilder();
            InputStream json = mainActivity.getAssets().open("MyJson.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;
            String jsonStr = "";
            while ((str = in.readLine()) != null) {
                jsonStr += str;
            }
            //Log.d("json", jsonStr);

            Type type = new TypeToken<List<ParentObject>>() {
            }.getType();

            list = new Gson().fromJson(jsonStr, type);

            in.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //let me start proving show the prove panel
   View.OnClickListener showProve=new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           proves.setVisibility(View.VISIBLE);
           givens.setVisibility(View.GONE);
           v.setVisibility(View.GONE);
       }

   };
//empty
    public void initSpinner(){

    }

}
