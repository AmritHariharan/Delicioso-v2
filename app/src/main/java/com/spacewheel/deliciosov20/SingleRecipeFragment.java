package com.spacewheel.deliciosov20;



import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleRecipeFragment extends Fragment {

    public SingleRecipeFragment() {
        // Required empty public constructor
    }


    ImageView imageView;
    Bitmap photo;
    Recipe recipe;
    final Context context = getActivity();
    MainActivity callingActivity;
    PackageManager packageManager;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_single_recipe_simple, container, false);

        callingActivity = (MainActivity) getActivity();

        packageManager = callingActivity.getPackageManager();

        EditText ingredients = (EditText) rootView.findViewById(R.id.editIngredients);
        EditText method = (EditText) rootView.findViewById(R.id.editMethod);
        TextView recipeName = (TextView) rootView.findViewById(R.id.recipeTitle);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.add_photo);
        ImageView emailImage = (ImageView) rootView.findViewById(R.id.emailImage);

        //dbManager = new DBManager(context);

        Bundle bundle = this.getArguments();
        recipe = new Recipe(
                bundle.getString("recipeTitle"),
                bundle.getString("recipeDescription"),
                bundle.getString("ingredients"),
                bundle.getString("method"),
                bundle.getString("notes"),
                bundle.getByteArray("imageId"),
                bundle.getString("parentBook")
        );

        ingredients.setText(recipe.getIngredients());
        method.setText(recipe.getMethod());
        recipeName.setText(recipe.getRecipeTitle());

        String TAGGER = "TAGGER";

        if (recipe.getImageId() != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(recipe.getImageId(), 0, recipe.getImageId().length);
            imageView.setImageBitmap(bmp);
        } else {

        }

        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Take a picture and pass the image along to onActivityResult
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

                return false;
            }
        });

        emailImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                //i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"deliciosorecipes@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, recipe.getRecipeTitle());
                i.putExtra(Intent.EXTRA_TEXT   ,
                        "Ingredients: \n" + recipe.getIngredients() + "\n \n" +
                        "Method: \n" + recipe.getMethod() + "\n \n" +
                        "Notes: \n" + recipe.getNotes() + "\n \n \n \n \n" +
                        "--------------------------------------------------------------- \n" +
                        "Made With ¡Delicioso!"
                );
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    MainActivity callingActivity = (MainActivity) getActivity();
                    Toast.makeText(callingActivity, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

        return rootView;
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            // Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            imageView.setImageBitmap(photo);
            // Convert to byte array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // Add image to db here
            (callingActivity.dbManager).addImageToRecipe(byteArray, recipe);
        }
    }

    /*

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();

            } catch (IOException ex) {
                // Error occurred while creating the File
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
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,   // prefix
                ".jpg",          // suffix
                storageDir       // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

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
        imageView.setImageBitmap(bitmap);
    }

    */

}
