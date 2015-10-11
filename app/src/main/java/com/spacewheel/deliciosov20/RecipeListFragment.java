package com.spacewheel.deliciosov20;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecipeListFragment extends Fragment {
    public RecipeListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        final String TAGX = "Test TAG";
        NavigationDrawerFragment mNavigationDrawerFragment;
        CharSequence mTitle;
        RecyclerView m2RecyclerView;
        RecyclerView.LayoutManager m2LayoutManager;
        BookRVAdapter m2Adapter;

        Context context = getActivity();

        DBManager dbManager = new DBManager(context);
        String parentBook = getArguments().getString("Book Name");

        mTitle = getString(R.string.title_section1);

        m2RecyclerView = (RecyclerView) rootView.findViewById(R.id.rv2);
        m2RecyclerView.setHasFixedSize(true);

        m2LayoutManager = new LinearLayoutManager(context);
        m2RecyclerView.setLayoutManager(m2LayoutManager);

        m2RecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Testing with toasts
                        Log.d(TAGX, "pls workaed");
                        Context context = view.getContext();
                        Toast.makeText(context, "test yo00, " + position, Toast.LENGTH_SHORT).show();

                        FragmentManager fragmentManager = getFragmentManager();
                        Fragment fragment;
                        fragment = new SingleRecipeFragment();
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, fragment)
                                .commit();
                    }
                })
        );

        List<Recipe> recipes;

        Recipe testRecipe1 = new Recipe("Recipe 1 in " + parentBook, "Test recipe", "No bugs, freedom", "Stir in pot for 20 mins", "Do on Android Studio", parentBook);
        Recipe testRecipe2 = new Recipe("Recipe 2 in " + parentBook, "Test recipe", "No bugs, freedom", "Stir in pot for 20 mins", "Do on Android Studio", parentBook);
        dbManager.addRecipe(testRecipe1);
        dbManager.addRecipe(testRecipe2);

        recipes = dbManager.getRecipes(parentBook);

        Recipe awd = new Recipe();
        awd.setRecipeTitle("WFWEF");
        recipes.add(awd);

        m2Adapter = new BookRVAdapter(recipes);
        m2RecyclerView.setAdapter(m2Adapter);

        return rootView;
    }



}
