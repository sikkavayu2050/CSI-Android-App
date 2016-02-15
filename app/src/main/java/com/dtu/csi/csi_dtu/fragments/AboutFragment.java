package com.dtu.csi.csi_dtu.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtu.csi.csi_dtu.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;

import java.util.ArrayList;

public class AboutFragment extends BaseFragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Adapter adapter;
    Boolean isInFront;
    public static String text,title;
    int [] photos = {R.drawable.poster2, R.drawable.poster3, R.drawable.poster4, R.drawable.poster5, R.drawable.poster6,R.drawable.poster7,R.drawable.poster8,};
    int [] images={R.drawable.dtu,R.drawable.bg};
    KenBurnsView imageView;
    public AboutFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_about);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.about_recycler);
        imageView = (KenBurnsView) rootView.findViewById(R.id.header_about);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<String> data = new ArrayList<>();
        data.add("About DTU");
        data.add("About CSI");
        adapter = new Adapter(data,images);
        recyclerView.setAdapter(adapter);
        AccelerateDecelerateInterpolator ACCELERATE_DECELERATE = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(5000, ACCELERATE_DECELERATE);
        imageView.setTransitionGenerator(generator);
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 3;
            public void run() {
                imageView.setImageResource(photos[i]);
                Drawable oriDrawable = imageView.getDrawable();
                oriDrawable.setCallback(null);

                // set callback to null

                System.gc();

                i++;
                if(i>photos.length-1){
                    i=0;
                }
                handler.postDelayed(this, 7000);  //for interval...
            }
        };
        handler.postDelayed(runnable, 100);
        return rootView;
    }


    @Override
    public void onPause() {
        super.onPause();
        isInFront = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        isInFront = true;
        adapter.setOnItemClickListener(new Adapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                title="CSI";
                Log.v(this.getClass().getSimpleName(), "Item Clicked " + position);
                if(position == 0) {
                    text = getString(R.string.about_dtu);
                    title = "DTU";
                } else {
                    title = "CSI";
                    text = "Welcoe to CSI\n" +
                            " \n        What is one of the best times for figuring out who you are and what you really want out of life ? Right after you enter college.\n" +
                            " \n" +
                            "       Lot of students make the mistake of living their college life in much the same way as they live their school life. But is college life the same as your school life ? The is a unanimous NO. \n" +
                            " \n" +
                            "       And the difference lies in the vast array of cultural and technical societies which are accessible in college. And here we are, talking about one such society, CSI.";
                }
                DFragment fragment = new DFragment();
                fragment.show(getFragmentManager(), "Dialog Fragment");
            }
        });
    }

    public static class Adapter extends RecyclerView.Adapter <AboutFragment.Adapter.DataObjectHolder> {
        MyClickListener myClickListener;
        ArrayList<String> aboutList;
        int []images;
        public Adapter(ArrayList<String> aboutList,int[] images) {
            this.aboutList = aboutList;
            this.images =images;
        }

        public void setOnItemClickListener(MyClickListener myClickListener) {
            this.myClickListener = myClickListener;
        }

        @Override
        public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_about_item, parent, false);
            return new DataObjectHolder(view);
        }

        @Override
        public void onBindViewHolder(DataObjectHolder holder, int position) {
            holder.label.setText(aboutList.get(position));
            holder.bgimage.setImageResource(images[position]);

        }

        public class DataObjectHolder extends RecyclerView.ViewHolder
                implements View
                .OnClickListener {
            TextView label;ImageView bgimage;
            public DataObjectHolder(View itemView) {
                super(itemView);
                bgimage =(ImageView)itemView.findViewById(R.id.bgimage);
                label = (TextView) itemView.findViewById(R.id.about_label);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                myClickListener.onItemClick(getAdapterPosition(), v);
            }
        }

        @Override
        public int getItemCount() {
            return aboutList.size();
        }

        public interface MyClickListener {
            void onItemClick(int position, View v);
        }
    }

}
