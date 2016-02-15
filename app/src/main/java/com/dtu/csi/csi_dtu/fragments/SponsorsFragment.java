package com.dtu.csi.csi_dtu.fragments;



import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.dtu.csi.csi_dtu.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SponsorsFragment extends BaseFragment {


    public SponsorsFragment() {
        // Required empty public constructor
    }


    public static final int INDEX = 0;
    protected AbsListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sponsors, container, false);
        listView = (ListView) rootView.findViewById(android.R.id.list);
        ((ListView) listView).setAdapter(new ImageAdapter(getActivity()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AnimateFirstDisplayListener.displayedImages.clear();
    }

    private class ImageAdapter extends BaseAdapter {

        private  final String[] IMAGE_URLS = {
                "https://drive.google.com/uc?id=0B3XKCinfQKVTaHNlRE44alJtUHc",
                "https://drive.google.com/uc?id=0B3XKCinfQKVTNWFCQXFoaXpJSm8",
                "https://drive.google.com/uc?id=0B3XKCinfQKVTN2FlOGpJRTJGTVU",
                "https://drive.google.com/uc?id=0B3XKCinfQKVTNmtWVURZRUtURDQ",
                "https://drive.google.com/uc?id=0B3XKCinfQKVTVFFmc0ZSSE1nOEU",
                "https://drive.google.com/uc?id=0B3XKCinfQKVTX053S0lNbGZWMmc",
                "https://drive.google.com/uc?id=0B3XKCinfQKVTdHVheHlYVlNiOGc",
                "https://drive.google.com/uc?id=0B3XKCinfQKVTZFJNQ2RkNHFzZWM",
        };

        private LayoutInflater inflater;
        private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

        private DisplayImageOptions options;

        ImageAdapter(Context context) {
            inflater = LayoutInflater.from(context);

            options = new DisplayImageOptions.Builder()
                    .displayer(new RoundedBitmapDisplayer(12))
                    .showImageOnLoading(R.drawable.csi_logo)
                    .showImageForEmptyUri(R.drawable.csi_logo)
                    .showImageOnFail(R.drawable.csi_logo)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .build();
        }

        @Override
        public int getCount() {
            return IMAGE_URLS.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            final ViewHolder holder;
            if (convertView == null) {
                view = inflater.inflate(R.layout.sponsors_list_image, parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) view.findViewById(R.id.image);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }


            ImageLoader.getInstance().init((ImageLoaderConfiguration.createDefault(getContext())));
            ImageLoader.getInstance()
                    .displayImage(IMAGE_URLS[position], holder.image, options, animateFirstListener);
            return view;
        }
    }

    class ViewHolder {
        ImageView image;
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}