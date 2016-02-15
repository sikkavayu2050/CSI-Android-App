package com.dtu.csi.csi_dtu.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.dtu.csi.csi_dtu.R;
import com.dtu.csi.csi_dtu.fragments.AboutFragment;
import com.dtu.csi.csi_dtu.fragments.BaseFragment;
import com.dtu.csi.csi_dtu.fragments.ContactFragment;
import com.dtu.csi.csi_dtu.fragments.EventsFragment;
import com.dtu.csi.csi_dtu.fragments.GalleryFragment;
import com.dtu.csi.csi_dtu.fragments.NewsFragment;
import com.dtu.csi.csi_dtu.fragments.SponsorsFragment;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                BaseFragment fragment;
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.news:
                        toolbar.setTitle("News Feed");
                        fragment = new NewsFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main, fragment).commit();
                        break;
                    case R.id.events:
                        toolbar.setTitle("Events");
                        fragment = new EventsFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main, fragment).commit();
                        break;
                    case R.id.gallery:
                        toolbar.setTitle("Gallery");
                        fragment = new GalleryFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main, fragment).commit();
                        break;
                    case R.id.sponsors:
                        toolbar.setTitle("Sponsors");
                        fragment = new SponsorsFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main, fragment).commit();
                        break;
                    case R.id.about:
                        toolbar.setTitle("About Us");
                        fragment = new AboutFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main, fragment).commit();
                        break;
                    case R.id.contact:
                        toolbar.setTitle("Contact Us");
                        fragment = new ContactFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main, fragment).commit();
                        break;
                    default:
                        toolbar.setTitle("News Feed");
                        fragment = new NewsFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main, fragment).commit();
                }
                return true;
            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                toolbar,
                R.string.openDrawer,
                R.string.closeDrawer);
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        toolbar.setTitle("News Feed");
        BaseFragment fragment = new NewsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, fragment).commit();

    }
    @Override
    protected void onResume() {
        super.onResume();
//        AppEventsLogger.activateApp(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            startActivity(new Intent(getApplicationContext(), AboutAppActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
