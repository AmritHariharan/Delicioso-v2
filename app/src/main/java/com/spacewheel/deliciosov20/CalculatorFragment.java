package com.spacewheel.deliciosov20;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    private final String[] unitTypes = { "Volume", "Mass/Weight", "Length", "Temperature" };

    public final String[] volumeOptions = { "Milliliter", "Liter", "Deciliter", "Tsp", "Tbsp", "Fl oz", "Gill", "Cup", "Pint", "Quart", "Gallon" };
    public final String[] massOptions = { "Gram", "Pound", "Ounce", "Milligram", "Kilogram" };
    public final String[] lengthOptions = { "Centimeter", "Millimeter", "Meter", "Inch" };

    Spinner typePicker;
    Spinner unitPicker1;
    Spinner unitPicker2;

    EditText numInput;
    TextView numOutput;

    Button convertButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.calculator_fragment, container, false);

        Context context = getActivity();

        calculator = new Calculator();

        typePicker = (Spinner) view.findViewById(R.id.conversionType);
        unitPicker1 = (Spinner) view.findViewById(R.id.spinner1);
        unitPicker2 = (Spinner) view.findViewById(R.id.spinner2);
        numInput = (EditText) view.findViewById(R.id.input_to_convert);
        numOutput = (TextView) view.findViewById(R.id.converted_value);
        convertButton = (Button) view.findViewById(R.id.convertButton);

        ArrayAdapter<String> spinnerArrayAdapterTypes = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, unitTypes); //selected item will look like a spinner set from XML
        spinnerArrayAdapterTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typePicker.setAdapter(spinnerArrayAdapterTypes);

        // Stuff for setting up the spinners

        final ArrayAdapter<String> spinnerArrayAdapterVolume = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, volumeOptions);
        spinnerArrayAdapterVolume.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> spinnerArrayAdapterMass = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, massOptions);
        spinnerArrayAdapterVolume.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> spinnerArrayAdapterLength = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, lengthOptions);
        spinnerArrayAdapterVolume.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        unitPicker1.setAdapter(spinnerArrayAdapterVolume);
        unitPicker2.setAdapter(spinnerArrayAdapterVolume);

        typePicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (typePicker.getSelectedItem().toString()) {
                    case "Volume":
                        unitPicker1.setAdapter(spinnerArrayAdapterVolume);
                        unitPicker2.setAdapter(spinnerArrayAdapterVolume);
                        break;
                    case "Mass/Weight":
                        unitPicker1.setAdapter(spinnerArrayAdapterMass);
                        unitPicker2.setAdapter(spinnerArrayAdapterMass);
                        break;
                    case "Length":
                        unitPicker1.setAdapter(spinnerArrayAdapterLength);
                        unitPicker2.setAdapter(spinnerArrayAdapterLength);
                        break;
                    case "Temperature":
                        //unitPicker1.setAdapter(spinnerArrayAdapterVolume);
                        //unitPicker2.setAdapter(spinnerArrayAdapterVolume);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnitButton();
            }
        });

        //ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, unitsList);

        return view;
    }

    public void convertUnitButton() {
        double value;

        value = calculator.convertUnits(
                unitTypes[0], // Type of conversion
                unitPicker1.getSelectedItem().toString(),
                unitPicker2.getSelectedItem().toString(),
                Double.parseDouble(numInput.getText().toString())
                );

        numOutput.setText(String.valueOf(value));
    }

}
