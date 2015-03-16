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

    // Making this class be generic for all type of errors
    // Can add custom title and message.
    // It's very important to add static, it's add option to access to setMessage by
    // AlertDialogFragment dialog = AlertDialogFragment.setMessage
    public static AlertDialogFragment setMessage(String title, String message) {
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String message = getArguments().getString("message");
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null);

        return builder.create();
    }
}
