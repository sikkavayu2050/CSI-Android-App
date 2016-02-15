package com.dtu.csi.csi_dtu.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.dtu.csi.csi_dtu.R;

public class AboutAppActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView gitLogo, facebookLogo, websiteLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        toolbar = (Toolbar) findViewById(R.id.toolbar_about);
        toolbar.setTitle("About the App");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gitLogo = (ImageView) findViewById(R.id.git_logo);
        gitLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(
                        Uri.parse("https://github.com/CSIAndroidApp/CSI-Android-App.git")));
            }
        });
        facebookLogo = (ImageView) findViewById(R.id.facebook_logo);
        facebookLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(
                        Uri.parse("https://www.facebook.com/PhoneixCSIDTU/?fref=ts")));
            }
        });
        websiteLogo = (ImageView) findViewById(R.id.web_logo);
        websiteLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(
                        Uri.parse("http://phoenix.csidtu.in/")));
            }
        });
    }
}
