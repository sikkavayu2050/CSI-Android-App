package com.dtu.csi.csi_dtu.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.dtu.csi.csi_dtu.activities.FullScreenGalleryActivity;
import com.dtu.csi.csi_dtu.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends BaseFragment {
    private final String LOG_TAG = GalleryFragment.class.getSimpleName();
    protected AbsListView listView;
    public   static String[] IMAGE_URLS = new String[]{
            "https://drive.google.com/uc?id=0B3XKCinfQKVTUnUzck1DMk9LWm8",
            "https://drive.google.com/uc?id=0B3XKCinfQKVTT0hZUlhQb01hOFk",
            "https://drive.google.com/uc?id=0B3XKCinfQKVTTFdOdF9PVDNDRUE",
            "https://drive.google.com/uc?id=0B3XKCinfQKVTM0JralU2SEljMnM",
            "https://drive.google.com/uc?id=0B3XKCinfQKVTLVBmanRRQXR2MGc",
            "https://drive.google.com/uc?id=0B3XKCinfQKVTMURaa2w1ZFhjbzQ",
            "https://drive.google.com/uc?id=0B3XKCinfQKVTZmhBTF95eUd3REU",
            "https://drive.google.com/uc?id=0B3XKCinfQKVTR090VTZIMGFXLVk",
            "https://drive.google.com/uc?id=0B3XKCinfQKVTWDRRMS15eFRXR2s",
            "https://drive.google.com/uc?id=0B3XKCinfQKVTYk5vcHV2dmtrNWs",
            "https://drive.google.com/uc?id=0B3XKCinfQKVTVmV4a1lfWS1uWW8"
            ,"https://drive.google.com/uc?id=0B3XKCinfQKVTV1BQZjBTd0o3VXc"
            ,"https://drive.google.com/uc?id=0B3XKCinfQKVTRElhYk54Y3RGZDQ"
            ,"https://drive.google.com/uc?id=0B3XKCinfQKVTN1dsZk90X1ZINXM"
            ,"https://drive.google.com/uc?id=0B3XKCinfQKVTTjFENWdCSkw3RzA"
            ,"https://drive.google.com/uc?id=0B3XKCinfQKVTLWd2anpwUG4zdnc"
            ,"https://drive.google.com/uc?id=0B3XKCinfQKVTekFiM1VHOHFzY2c"
            ,"https://drive.google.com/uc?id=0B3XKCinfQKVTdFVFcTJ4MWdJdDg"
            ,"https://drive.google.com/uc?id=0B3XKCinfQKVTbk1NY2xiODZjSWc"
            ,"https://drive.google.com/uc?id=0B3XKCinfQKVTNmxKLU8wRnRDSnc"
            ,"https://drive.google.com/uc?id=0B3XKCinfQKVTaHRHX1VXQmtSbzg"};
    public GalleryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_gallery, container, false);
        listView = (GridView) rootView.findViewById(R.id.gridview);
        ((GridView) listView).setAdapter(new ImageAdapter(getActivity()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startImagePagerActivity(position);
            }
        });
        return rootView;
    }
    /**
     * TODO: initializes with image urls
     */
    void startImagePagerActivity(int position)
    {
        Intent intent = new Intent(getActivity(), FullScreenGalleryActivity.class);
        intent.putExtra(Extra.IMAGE_POSITION, position);
        startActivity(intent);
    }
    public static class Extra {
        public static final String IMAGE_POSITION = "com.nostra13.example.universalimageloader.IMAGE_POSITION";
    }

    static class ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
    }
    private class ImageAdapter extends BaseAdapter
    {
        private LayoutInflater inflater;
        private DisplayImageOptions options;
        ImageAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.csi_logo)
                    .showImageForEmptyUri(R.drawable.csi_logo)
                    .showImageOnFail(R.drawable.csi_logo)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }

        @Override
        public int getCount() {
           // TODO: 06-01-2016  no. of images 21 hardcoded
            return 21;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            View view = convertView;

            if (view == null) {
                view = inflater.inflate(R.layout.item_grid_image, parent, false);
                holder = new ViewHolder();
                assert view != null;
                holder.imageView = (ImageView) view.findViewById(R.id.image);
                holder.progressBar = (ProgressBar) view.findViewById(R.id.progress);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            ImageLoader.getInstance().init((ImageLoaderConfiguration.createDefault(getContext())));
            ImageLoader.getInstance()
                    .displayImage(IMAGE_URLS[position], holder.imageView, options, new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
                            holder.progressBar.setProgress(0);
                            holder.progressBar.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            holder.progressBar.setVisibility(View.GONE);
                        }
                    }, new ImageLoadingProgressListener() {
                        @Override
                        public void onProgressUpdate(String imageUri, View view, int current, int total) {
                            holder.progressBar.setProgress(Math.round(100.0f * current / total));
                        }
                    });

            return view;
        }
    }

    /**
     *
     * @param JsonData Json Object
     * @return String[] with img urls
     * @throws JSONException
     */
    private String[] getUrlFromJson(String JsonData)
            throws JSONException {
        // TODO: 06-01-2016  change IMAGE_URL to gallery
        String[] gallery = new String[18];
        JSONObject jsonObject = new JSONObject(JsonData);
        JSONArray jgallery = jsonObject.getJSONArray("gallery");
        JSONObject arrayObj;
        for(int i=0;i<jgallery.length();i++)
         {
             arrayObj = jgallery.getJSONObject(i);
             gallery[i] = arrayObj.getString("image");

         }
        for (String s : gallery)
        {
                Log.v(LOG_TAG, "image url" + s);
        }
        return gallery;
    }
    public class fetchData extends AsyncTask<Void,Void,String[]>
    {
        /**
         * @return image urls
         */
         @Override
        protected String[] doInBackground(Void...Params)
        {   String JsonData;
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try{
                URL url = new URL("http://csidtu.site88.net/");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                 }
                JsonData = buffer.toString();
                Log.v(LOG_TAG, "Gallery JSON String" + JsonData);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the data, there's no point in attemping
                // to parse it.
                return null;
                } finally {
                    if (urlConnection != null) {
                    urlConnection.disconnect();
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (final IOException e) {
                            Log.e(LOG_TAG, "Error closing stream", e);
                         }
                    }
                }
        try {
            return getUrlFromJson(JsonData);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }
        return null;
        }
    }
}
