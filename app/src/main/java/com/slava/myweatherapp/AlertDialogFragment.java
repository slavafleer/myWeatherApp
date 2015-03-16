package com.slava.myweatherapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Slava on 16/03/2015.
 */
public class AlertDialogFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Error Alert")
                .setMessage("There is an error.")
                .setPositiveButton("OK", null);

        return builder.create();
    }
}
