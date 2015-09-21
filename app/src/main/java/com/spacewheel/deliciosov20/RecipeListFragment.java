package com.spacewheel.deliciosov20;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class RecipeListFragment extends Fragment {

    public RecipeListFragment() {

    }

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence m2Title;
    private RecyclerView m2RecyclerView;
    RecyclerView.LayoutManager m2LayoutManager;
    BookRVAdapter m2Adapter;
    private List<Recipe> recipes;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        Context context = getActivity();

        m2Title = getString(R.string.title_section1);

        m2RecyclerView = (RecyclerView) rootView.findViewById(R.id.rv2);

        m2LayoutManager = new LinearLayoutManager(context);
        m2RecyclerView.setLayoutManager(m2LayoutManager);

        m2RecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Testing with toasts
                        Context context = view.getContext();
                        Toast.makeText(context, "test yo00, " + position, Toast.LENGTH_SHORT).show();
                    }
                })
        );

        m2Adapter = new BookRVAdapter(recipes);
        m2RecyclerView.setAdapter(m2Adapter);

        return rootView;
    }

}
