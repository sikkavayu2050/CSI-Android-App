package com.dtu.csi.csi_dtu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.dtu.csi.csi_dtu.DividerItemDecoration;
import com.dtu.csi.csi_dtu.EventItem;
import com.dtu.csi.csi_dtu.EventsAdapter;
import com.dtu.csi.csi_dtu.R;
import com.dtu.csi.csi_dtu.activities.EventActivity;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;

public class EventsFragment extends BaseFragment implements View.OnTouchListener{
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mRecyclerAdapter;
    RecyclerView.LayoutManager mRecyclerLayoutManager;
    ArrayList<EventItem> data;
    int [] images={R.drawable.cover, R.drawable.hasta_la_vista, R.drawable.avantgarde,R.drawable.treasurehunt,R.drawable.orientation};
    String[] event_name={"Cogenesis","Hasta La Vista","Avant-Garde","Treasure Hunt","Orientation" };
    int[] clickPoint = new int[2];
    int[] numberOfEvents={11,5,5,5,5};
    public EventsFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.events);
        rootView.setOnTouchListener(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mRecyclerLayoutManager);
        //EventItem eventItem = new EventItem(R.drawable.bg, R.drawable.bg, "Phoenix");
        EventItem[] items = new EventItem[5];
        EventDetailsFragment.generateEvents();
        for(int i=0;i<5;i++)
         items[i]=new EventItem(images[i],R.drawable.csi_logo,event_name[i]);
        //Arrays.fill(items, eventItem);
        data = new ArrayList<>(Arrays.asList(items));
        mRecyclerAdapter = new EventsAdapter(data);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((EventsAdapter) mRecyclerAdapter).setOnItemClickListener(new
            EventsAdapter.MyClickListener() {
                @Override
            public void onItemClick(int position, View v) {
                    Intent intent = new Intent(getContext(), EventActivity.class);
                    intent.putExtra("Position", position);
                    intent.putExtra("start_point", clickPoint);
                    intent.putExtra("number_of_events",numberOfEvents[position]);
                    startActivity(intent);
                    getActivity().overridePendingTransition(0, 0);
                }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        clickPoint[0] = (int) event.getX();
        clickPoint[1] = (int) event.getY();
        Log.v(this.getClass().getSimpleName(), "Touch Event");
        return false;
    }
}
