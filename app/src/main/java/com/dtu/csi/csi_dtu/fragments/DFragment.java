package com.dtu.csi.csi_dtu.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dtu.csi.csi_dtu.R;

public class DFragment extends DialogFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog, container, false);
        ((TextView)rootView.findViewById(R.id.about_text)).setText(AboutFragment.text);
        getDialog().setTitle(AboutFragment.title);
        getDialog().show();
        return rootView;
    }
}
