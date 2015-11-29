package com.spacewheel.deliciosov20;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

import net.bozho.easycamera.DefaultEasyCamera;
import net.bozho.easycamera.EasyCamera;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleRecipeFragment extends Fragment {

    public SingleRecipeFragment() {
        // Required empty public constructor
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    Bitmap photo;
    Recipe recipe;
    //final Context context = getActivity();

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

        Bundle bundle = this.getArguments();
        recipe = new Recipe(
                bundle.getString("recipeTitle"),
                bundle.getString("recipeDescription"),
                bundle.getString("ingredients"),
                bundle.getString("method"),
                bundle.getString("notes"),
                bundle.getInt("imageId"),
                bundle.getString("parentBook")
        );

        ingredients.setText(recipe.getIngredients());
        method.setText(recipe.getMethod());
        recipeName.setText(recipe.getRecipeTitle());

        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Take a picture and pass the image along to onActivityResult
                //startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

                // EASYCAM IMPLEMENTATION

                EasyCamera camera = DefaultEasyCamera.open();
                EasyCamera.CameraActions actions = camera.startPreview(surface);
                EasyCamera.PictureCallback callback = new EasyCamera.PictureCallback() {
                    public void onPictureTaken(byte[] data, EasyCamera.CameraActions actions) {
                        // store picture
                        // This works, but it takes forever...
                        //photo = BitmapFactory.decodeResource(getResources(), R.drawable.drawer_image);
                        //ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        //photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        //byte[] byteArray = stream.toByteArray();
                        ((MainActivity)getActivity()).dbManager.addImageToRecipe(data, recipe);
                    }
                };
                actions.takePicture(EasyCamera.Callbacks.create().withJpegCallback(callback));


                // SQUARECAM IMPLEMENTATION




                //dispatchTakePictureIntent();
                //setPic(imageView);3
                return false;
            }
        });

        return rootView;
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            // Get the photo
            Bundle extras = data.getExtras();
            photo = (Bitmap) extras.get("data");
            imageView.setImageBitmap(photo);

        }

    }*/

    /*private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    String mCurrentPhotoPath;




    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); // Check date import, might be sql not util
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private void setPic(ImageView mImageView) {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }

    private boolean hasCamera() {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    */

}
