package com.dtu.csi.csi_dtu.fragments;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dtu.csi.csi_dtu.R;

import butterknife.Bind;

public class ContactFragment extends BaseFragment implements View.OnClickListener{
    @Bind(R.id.ratingBar)
    RatingBar ratingBar;
    @Bind(R.id.feedback)
    EditText feedback;
    @Bind(R.id.sendFeedback)
    Button sendFeedback;
    Intent feedbackIntent;
    @Bind(R.id.contact1)
    TextView contact1;
    @Bind(R.id.contact2)
    TextView contact2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_contact);
        sendFeedback.setOnClickListener(this);
        contact1.setPaintFlags(contact1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        contact2.setPaintFlags(contact2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = contact1.getText().toString();
                startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" +
                        s.substring(s.indexOf(":")).trim()
                )));
            }
        });
        contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = contact2.getText().toString();
                startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" +
                        s.substring(s.indexOf(":")).trim()
                )));
            }
        });
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        sendFeedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.v(this.getClass().getSimpleName(), "Clicked");
        feedbackIntent = new Intent(Intent.ACTION_SEND);
        feedbackIntent.setData(Uri.parse("mailto:earpitg@gmail.com"));
        feedbackIntent.setType("text/plain");/*
        feedbackIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"earpitg@gmail.com"});*/
        feedbackIntent.putExtra(Intent.EXTRA_SUBJECT, "CSI-DTU Application Feedback");
        String feedbackText = "Rating : " + ratingBar.getRating() + "\n";
        feedbackText += feedback.getText().toString();
        feedbackIntent.putExtra(Intent.EXTRA_TEXT, feedbackText);
        startActivity(feedbackIntent);
    }
}
