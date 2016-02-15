package com.dtu.csi.csi_dtu.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.dtu.csi.csi_dtu.MainPagerAdapter;
import com.dtu.csi.csi_dtu.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;

import java.util.ArrayList;
import java.util.List;

import andy.ayaseruri.lib.CircularRevealActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

public class EventActivity extends CircularRevealActivity{
    int position;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    int numberOfTabs ;
    List<String> listItems = new ArrayList<String>();
    MainPagerAdapter adapter;
    @Bind(R.id.header_activity)
    KenBurnsView header;
    int i = 0;
    int headers[] = {R.drawable.poster2, R.drawable.poster3, R.drawable.poster4, R.drawable.poster5, R.drawable.poster6,R.drawable.poster7,R.drawable.poster8};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        numberOfTabs = getIntent().getIntExtra("number_of_events",0);
        position = getIntent().getIntExtra("Position", 0);
        ButterKnife.bind(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolbar);
        CoordinatorLayout layout;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AccelerateDecelerateInterpolator ACCELERATE_DECELERATE = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(5000, ACCELERATE_DECELERATE);
        header.setTransitionGenerator(generator);
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 5;
            public void run() {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), headers[i]);
                header.setImageBitmap(Bitmap.createScaledBitmap(bmp, 200, 200, false));
                bmp.recycle();
                Drawable oriDrawable = header.getDrawable();
                oriDrawable.setCallback(null);
                System.gc();
                i++;
                if(i > 5)
                    i = 0;
                handler.postDelayed(this, 7000);  //for interval...
            }
        };
        handler.postDelayed(runnable, 100);
        setUpTabs();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    void setUpTabs(){
        switch (numberOfTabs) {
            case 11:
                listItems.add("CODE GOLF");
                listItems.add("CRYPTEX");
                listItems.add("NEUVO GENGO");
                listItems.add("CRANIUM");
                listItems.add("ZNAPZ");
                listItems.add("PANCRATIUM");
                listItems.add("TROLL-IT");
                listItems.add("MATHRIX");
                listItems.add("SUDO CODE");
                listItems.add("MIND MUMBLE");
                listItems.add("SMASH DUB");
                listItems.add("IDEATE");
                listItems.add("COGITATE");
                listItems.add("THREE LINES OF CODE");
                listItems.add("SWITCH PROGRAMMING");
                listItems.add("TESTING GEEKS");
                listItems.add("MACHINE LEARNING MANIA");
                listItems.add("BUG TRAIL");
                listItems.add("CODEWHIZ");
                listItems.add("DTU GREAT MARATHON");
                listItems.add("SHADES OF MYSTERY");
                listItems.add("CODEFEST");
                listItems.add("PAPER PRESENTATION");
                listItems.add("CONFERENCE");
                break;
        }
        CharSequence[] tabTitles = listItems.toArray(new CharSequence[listItems.size()]);
        adapter =  new MainPagerAdapter(this.getSupportFragmentManager(), tabTitles,listItems.size());
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);
    }
}
