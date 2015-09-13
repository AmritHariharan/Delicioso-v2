package com.spacewheel.deliciosov20;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Amrit on 18/08/2015.
 */
public class Calculator extends Fragment {
        public Calculator() {

        }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.calculator_fragment, container, false);

        Spinner unitPicker1 = (Spinner) view.findViewById(R.id.spinner1);
        Spinner unitPicker2 = (Spinner) view.findViewById(R.id.spinner2);
        ArrayList<String> unitsList = new ArrayList<String>();
        unitsList.add("Grams");
        unitsList.add("Kilograms");

        EditText numInput = (EditText) view.findViewById(R.id.input_to_convert);
        TextView numOutput = (TextView) view.findViewById(R.id.converted_value);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, unitsList);

        return view;
    }
}
