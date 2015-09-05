package com.spacewheel.deliciosov20;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateBookFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


/**
 * Created by Amrit on 18/8/15.
 */
public class CreateBookFragment extends DialogFragment {

    EditText newBookName, newBookDescription;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        final View view = inflater.inflate(R.layout.fragment_create_book, null);

                // Pass null as the parent view because its going in the dialog layout

                builder.setView(view)
                        // Add action buttons
                        .setPositiveButton("Create Recipe Book", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                newBookName = (EditText) view.findViewById(R.id.new_book_name);
                                newBookDescription = (EditText) view.findViewById(R.id.new_book_description);

                                String book_name = newBookName.getText().toString();
                                String book_description = newBookDescription.getText().toString();
                                Log.d("Name:  ", book_name);
                                Log.d("Description: ", book_description);
                                MainActivity callingActivity = (MainActivity) getActivity();
                                callingActivity.onUserSelectValue(book_name, book_description);
                                dialog.dismiss();

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                CreateBookFragment.this.getDialog().cancel();
                            }
                        });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void onClick(DialogInterface dialog, int position) {


    }


}
