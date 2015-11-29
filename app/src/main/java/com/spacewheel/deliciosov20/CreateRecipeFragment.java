package com.spacewheel.deliciosov20;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateRecipeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CreateRecipeFragment extends DialogFragment {

    public CreateRecipeFragment() {
        // Required empty public constructor
    }

    EditText newName, newIngredients, newMethod, newNotes;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        final View view = inflater.inflate(R.layout.fragment_create_recipe, null);

        // Pass null as the parent view because its going in the dialog layout

        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Create Recipe", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        newName = (EditText) view.findViewById(R.id.editTitle);
                        newIngredients = (EditText) view.findViewById(R.id.editIngredients);
                        newMethod = (EditText) view.findViewById(R.id.editMethod);
                        newNotes = (EditText) view.findViewById(R.id.editNotes);

                        String recipe_name = newName.getText().toString();
                        String recipe_ingredients = newIngredients.getText().toString();
                        String recipe_method = newMethod.getText().toString();
                        String recipe_notes = newNotes.getText().toString();

                        Recipe recipe = new Recipe(recipe_name, "Description", recipe_ingredients, recipe_method, recipe_notes, "Parent Book");

                        Log.d("Name:  ", recipe_name);
                        MainActivity callingActivity = (MainActivity) getActivity();
                        callingActivity.addRecipeFromFragment(recipe);
                        dialog.dismiss();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CreateRecipeFragment.this.getDialog().cancel();
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void onClick(DialogInterface dialog, int position) {


    }

}
