package com.spacewheel.deliciosov20;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Amrit on 17/08/2015.
 */
public class CustomRVAdapter extends RecyclerView.Adapter<CustomRVAdapter.CustomViewHolder> {

    String TAGSER = "Test TAG";

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView bookName;
        TextView bookDescription;
        ImageView bookPhoto;
        Context context;

        CustomViewHolder (View v) {
            super(v);
            cv = (CardView) v.findViewById(R.id.cv);
            bookName = (TextView) v.findViewById(R.id.book_name);
            bookDescription = (TextView) v.findViewById(R.id.book_description);
            bookPhoto = (ImageView) v.findViewById(R.id.book_photo);
            context = v.getContext();
        }
    }

    List<RecipeBook> recipeBooks;

    CustomRVAdapter(List<RecipeBook> recipeBooks) {
        this.recipeBooks = recipeBooks;
    } // This is a setter (i think)

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_card_view, viewGroup, false);
        CustomViewHolder cvh = new CustomViewHolder(v);
        return cvh;

    }

    @Override
    public void onBindViewHolder(CustomViewHolder cvh, int i) {
        cvh.bookName.setText(recipeBooks.get(i).bookTitle);
        cvh.bookDescription.setText(recipeBooks.get(i).bookDescription);
        cvh.bookPhoto.setImageResource(recipeBooks.get(i).bookPhotoId);
    }

    @Override
    public int getItemCount() {
        return recipeBooks.size();
    }
}
