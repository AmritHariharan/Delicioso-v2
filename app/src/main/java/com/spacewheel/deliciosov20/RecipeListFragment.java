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

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeListFragment extends Fragment {
    public RecipeListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        final List<Recipe> recipes;

        final String TAGX = "Test TAG";
        NavigationDrawerFragment mNavigationDrawerFragment;
        CharSequence mTitle;
        RecyclerView m2RecyclerView;
        RecyclerView.LayoutManager m2LayoutManager;
        final BookRVAdapter m2Adapter;
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.create_new_recipe);

        Bundle bundle = this.getArguments();
        mTitle = bundle.getString("Book Name"); // Test if this works
        Log.d(TAGX, "RECIPE TITLE: " + mTitle);

        Context context = getActivity();
        final DBManager dbManager = new DBManager(context);
        String parentBook = getArguments().getString("Book Name");

        m2RecyclerView = (RecyclerView) rootView.findViewById(R.id.rv2);
        m2RecyclerView.setHasFixedSize(true);

        m2LayoutManager = new LinearLayoutManager(context);
        m2RecyclerView.setLayoutManager(m2LayoutManager);

        recipes = dbManager.getRecipes(parentBook);
        m2Adapter = new BookRVAdapter(recipes);
        m2RecyclerView.setAdapter(m2Adapter);

        Collections.sort(recipes, new RecipeComparator());

        m2RecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Testing with toasts
                        Log.d(TAGX, "pls workaed");
                        Context context = view.getContext();
                        Toast.makeText(context, "test yo00, " + position, Toast.LENGTH_LONG).show();

                        Log.d(TAGX, recipes.get(position).toString());
                        Recipe currentRecipe = recipes.get(position);


                        Bundle bundle = new Bundle();
                        bundle.putString("recipeTitle", currentRecipe.getRecipeTitle());
                        bundle.putString("recipeDescription", currentRecipe.getRecipeDescription());
                        bundle.putString("ingredients", currentRecipe.getIngredients());
                        bundle.putString("method", currentRecipe.getMethod());
                        bundle.putString("notes", currentRecipe.getNotes());
                        bundle.putByteArray("imageId", currentRecipe.getImageId());
                        bundle.putString("parentBook", currentRecipe.getParentBook());

                        FragmentManager fragmentManager = getFragmentManager();
                        Fragment fragment;
                        fragment = new SingleRecipeFragment();
                        fragment.setArguments(bundle);
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, fragment)
                                .commit();
                    }
                })
        );

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateRecipeFragment createRecipeFragment = new CreateRecipeFragment();
                MainActivity callingActivity = (MainActivity) getActivity();
                createRecipeFragment.show(callingActivity.getSupportFragmentManager(), "DialogBOX2");
                //recipes.add(callingActivity.tempRecipe);
                //dbManager.addRecipe(callingActivity.tempRecipe);
                m2Adapter.notifyDataSetChanged();
            }
        });

        //Recipe testRecipe1 = new Recipe("Recipe 1 in " + parentBook, "Test recipe", "No bugs, freedom", "Stir in pot for 20 mins \n" + Math.random(), "Do on Android Studio", parentBook);
        //Recipe testRecipe2 = new Recipe("Recipe 2 in " + parentBook, "Test recipe", "No bugs, freedom", "Stir in pot for 20 mins \n" + Math.random(), "Do on Android Studio", parentBook);
        //dbManager.addRecipe(testRecipe1);
        //dbManager.addRecipe(testRecipe2);

        return rootView;
    }



}
