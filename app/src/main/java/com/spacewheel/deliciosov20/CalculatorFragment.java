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
import java.util.Arrays;

/**
 * Created by Amrit on 15/10/15.
 */
public class CalculatorFragment extends Fragment {

    public CalculatorFragment() {

    }

    Calculator calculator;

    private String conversionType;

    private final String[] unitTypes = { "Volume", "Mass/Weight", "Length", "Temperature" };

    public final String[] volumeOptions = { "Milliliter", "Liter", "Deciliter", "Tsp", "Tbsp", "Fl oz", "Gill", "Cup", "Pint", "Quart", "Gallon" };
    public final String[] massOptions = { "Gram", "Pound", "Ounce", "Milligram", "Kilogram" };
    public final String[] lengthOptions = { "Centimeter", "Millimeter", "Meter", "Inch" };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.calculator_fragment, container, false);

        calculator = new Calculator();

        Spinner unitPicker1 = (Spinner) view.findViewById(R.id.spinner1);
        Spinner unitPicker2 = (Spinner) view.findViewById(R.id.spinner2);
        ArrayList<String> unitsList = new ArrayList<String>();

        ArrayAdapter<String> spinnerArrayAdapterVolume = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Arrays.asList(volumeOptions)); //selected item will look like a spinner set from XML
        spinnerArrayAdapterVolume.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitPicker1.setAdapter(spinnerArrayAdapterVolume);

        EditText numInput = (EditText) view.findViewById(R.id.input_to_convert);
        TextView numOutput = (TextView) view.findViewById(R.id.converted_value);

        //ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, unitsList);

        return view;
    }

}
