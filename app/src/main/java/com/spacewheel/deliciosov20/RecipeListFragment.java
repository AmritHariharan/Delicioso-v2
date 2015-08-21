package com.spacewheel.deliciosov20;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Amrit on 18/8/15.
 */
public class RecipeListFragment extends DialogFragment {

    EditText new_book_name, new_book_description;
    String string;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout


        builder.setView(inflater.inflate(R.layout.fragment_create_book, null))
                // Add action buttons
                .setPositiveButton("Create Recipe Book", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        new_book_name = (EditText) getActivity().findViewById(R.id.new_book_name);
                        string = (String) new_book_name.getText().toString();

                        new_book_description = (EditText) getActivity().findViewById(R.id.new_book_description);

                        Log.d("TAGX", string);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RecipeListFragment.this.getDialog().cancel();
                    }
                });



        // Create the AlertDialog object and return it
        return builder.create();
    }
}
