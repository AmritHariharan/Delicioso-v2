package com.spacewheel.deliciosov20;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.net.URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleRecipeFragment extends Fragment {

    public SingleRecipeFragment() {
        // Required empty public constructor
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    final Context context = getActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_single_recipe_simple, container, false);

        EditText ingredients = (EditText) rootView.findViewById(R.id.editIngredients);
        EditText method = (EditText) rootView.findViewById(R.id.editMethod);
        TextView recipeName = (TextView) rootView.findViewById(R.id.recipeTitle);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.add_photo);

        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //Take a picture and pass the image along to onActivityResult
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

                return false;
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            // Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            imageView.setImageBitmap(photo);

        }

    }

    private boolean hasCamera() {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }


}
