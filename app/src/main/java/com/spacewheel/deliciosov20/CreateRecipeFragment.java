package com.spacewheel.deliciosov20;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
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
public class CreateRecipeFragment extends Fragment {

    public CreateRecipeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_recipe, container, false);

        Context context = getActivity();

        final MainActivity mainActivity = (MainActivity) getActivity();

        Button addRecipeButton = (Button) view.findViewById(R.id.addButton);

        final EditText editMethod = (EditText) view.findViewById(R.id.editMethod);
        final EditText editIngredients = (EditText) view.findViewById(R.id.editIngredients);

        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String method = editMethod.getText().toString();
                String ingredients = editIngredients.getText().toString();

                Recipe recipe = new Recipe();
                mainActivity.addRecipeFromFragment(recipe);
            }
        });

        return view;
    }

    public void addRecipeClick(View view) {

    }

}
