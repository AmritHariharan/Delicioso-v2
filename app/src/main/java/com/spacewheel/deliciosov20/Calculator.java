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

    private final String[] unitTypes = { "Volume", "Mass/Weight", "Length", "Temperature" };

    public final String[] volumeOptions = { "Milliliter", "Liter", "Deciliter", "Tsp", "Tbsp", "Fl oz", "Gill", "Cup", "Pint", "Quart", "Gallon" };
    public final String[] massOptions = { "Gram", "Pound", "Ounce", "Milligram", "Kilogram" };
    public final String[] lengthOptions = { "Centimeter", "Millimeter", "Meter", "Inch" };

    // Multiply by these numbers
    // Volume
    private final double TSP_TO_ML = 4.92892;
    private final double TBSP_TO_ML = 14.7868;
    private final double FLOZ_TO_ML = 29.5735;
    private final double GILL_TO_ML = 118.294;
    private final double CUP_TO_ML = 236.588;
    private final double PINT_TO_ML = 473.176;
    private final double QUART_TO_ML = 946.353;
    private final double GALLON_TO_ML = 3785.41;
    private final double L_TO_ML = 1000;
    private final double DL_TO_ML = 100;

    // Mass/Weight
    private final double POUND_TO_G = 453.592;
    private final double OUNCE_TO_G = 28.3495;
    private final double MG_TO_G = 0.001;
    private final double KG_TO_G = 1000;

    // Length
    private final double MM_TO_CM = 0.1;
    private final double M_TO_CM = 100;
    private final double INCH_TO_CM = 2.54;

    private double convertVolume (double value, String unit, String toFrom) {
        double multiplier = 1;

        switch (unit) {
            case "Milliliter":
                // Convert ML to ML --> Do nothing
                break;
            case "Liter":
                multiplier = L_TO_ML;
                break;
            case "Deciliter":
                multiplier = DL_TO_ML;
                break;
            case "Tsp":
                multiplier = TSP_TO_ML;
                break;
            case "Tbsp":
                multiplier = TBSP_TO_ML;
                break;
            case "Fl oz":
                multiplier = FLOZ_TO_ML;
                break;
            case "Gill":
                multiplier = GILL_TO_ML;
                break;
            case "Cup":
                multiplier = CUP_TO_ML;
                break;
            case "Pint":
                multiplier = PINT_TO_ML;
                break;
            case "Quart":
                multiplier = QUART_TO_ML;
                break;
            case "Gallon":
                multiplier = GALLON_TO_ML;
                break;
        }

        if (toFrom.equals("FROM")) {
            multiplier = 1 / multiplier;
        }

        value = value * multiplier;

        return value;

    }

    private double convertWeight (double value, String unit, String toFrom) {
        double multiplier = 1;

        switch (unit) {
            case "Gram":
                // Convert G to G --> Do nothing
                break;
            case "Pound":
                multiplier = POUND_TO_G;
                break;
            case "Ounce":
                multiplier = OUNCE_TO_G;
                break;
            case "Milligram":
                multiplier = MG_TO_G;
                break;
            case "Kilogram":
                multiplier = KG_TO_G;
                break;
        }

        if (toFrom.equals("FROM")) {
            multiplier = 1 / multiplier;
        }

        value = value * multiplier;

        return value;

    }

    private double convertLength (double value, String unit, String toFrom) {
        double multiplier = 1;

        switch (unit) {
            case "Centimeter":
                // Convert CM to CM --> Do nothing
                break;
            case "Millimeter":
                multiplier = MM_TO_CM;
                break;
            case "Meter":
                multiplier = M_TO_CM;
                break;
            case "Inch":
                multiplier = INCH_TO_CM;
                break;
        }

        if (toFrom.equals("FROM")) {
            multiplier = 1 / multiplier;
        }

        value = value * multiplier;

        return value;

    }


    private double convert_f_to_c (double value) {
        return ((value-32)/1.8);
    }

    private double convert_c_to_f (double value) {
        return ((value*1.8)+32);
    }
}
