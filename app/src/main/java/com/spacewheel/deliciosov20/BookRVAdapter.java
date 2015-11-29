package com.spacewheel.deliciosov20;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Amrit on 16/9/15.
 */

public class BookRVAdapter extends RecyclerView.Adapter<BookRVAdapter.CustomViewHolderBook> {

    String TAGSE = "Test TAawdawdaw";

    public class CustomViewHolderBook extends RecyclerView.ViewHolder {
        CardView cv;
        TextView recipeName;
        TextView recipeDescription;
        Context context;
        ImageView imageView;

        CustomViewHolderBook (View v) {
            super(v);
            cv = (CardView) v.findViewById(R.id.cv);
            recipeName = (TextView) v.findViewById(R.id.book_name);
            recipeDescription = (TextView) v.findViewById(R.id.book_description);
            context = v.getContext();
        }
    }

    List<Recipe> recipes;

    BookRVAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    } // This is a setter (i think)

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public CustomViewHolderBook onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_card_view, viewGroup, false);
        CustomViewHolderBook cvh = new CustomViewHolderBook(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CustomViewHolderBook cvh, int i) {
        try {
            cvh.recipeName.setText(recipes.get(i).recipeTitle);
            cvh.recipeDescription.setText(recipes.get(i).recipeDescription);
        } catch (Exception e) {

        }
        //cvh.imageView.setImageBitmap();
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}