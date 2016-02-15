package com.dtu.csi.csi_dtu.fragments;

import android.content.Context;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dtu.csi.csi_dtu.MySingleton;
import com.dtu.csi.csi_dtu.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.victor.loading.newton.NewtonCradleLoading;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class NewsFragment extends BaseFragment{
    RecyclerView news;
    //String[] newsLines = new String[10];
    //int[] newsPhotos = new int[10];
    ArrayList<String> newsLines = new ArrayList<>();
    ArrayList<String> newsPhotos = new ArrayList<>();
    ArrayList<String> newsDates = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    NewtonCradleLoading newton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_news);
        if(!isNetworkAvailable()) {
            ImageView disconnected = (ImageView) rootView.findViewById(R.id.disconnected);
            disconnected.setVisibility(View.VISIBLE);
            return rootView;
        }
        ImageView disconnected = (ImageView) rootView.findViewById(R.id.disconnected);
        disconnected.setVisibility(View.INVISIBLE);
        news = (RecyclerView) rootView.findViewById(R.id.news_list);
        newton = (NewtonCradleLoading) rootView.findViewById(R.id.newton);
        newton.setVisibility(View.GONE);
        news.setHasFixedSize(true);
        news.setLayoutManager(new LinearLayoutManager(getContext()));
        requestFeed();
        return rootView;
    }
    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{
        ArrayList<String> photos;
        ArrayList<String> headlines;
        ArrayList<String> dates;
        ImageLoader imageLoader;
        DisplayImageOptions options;
        public CustomAdapter (ArrayList<String> photos, ArrayList<String> headlines, ArrayList<String> dates) {
            this.photos = photos;
            this.headlines =  headlines;
            this.dates = dates;
            imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
            options = new DisplayImageOptions.Builder().imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();
        }
        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.news_item, null);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            if(photos.size() > 0 && (photos.get(position).length() != 0)) {
                if (imageLoader.isInited())
                    imageLoader.displayImage(Uri.parse(photos.get(position)).toString(), holder.newsPhoto, options);
                else
                    imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
            }
            else {
                holder.newsPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
                holder.newsPhoto.setImageResource(R.drawable.cover);
            }
            holder.newsLine.setText(headlines.get(position));
            String date = dates.get(position).replace('T', ' ');
            holder.date.setText(date.substring(0, date.indexOf("+")));
        }

        @Override
        public int getItemCount() {
            return headlines.size();
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {
            ImageView newsPhoto;
            TextView newsLine, date;
            public CustomViewHolder(View itemView) {
                super(itemView);
                newsPhoto = (ImageView) itemView.findViewById(R.id.news_photo1);
                newsLine = (TextView) itemView.findViewById(R.id.news_line);
                date = (TextView) itemView.findViewById(R.id.date);
            }
        }
    }
    public void requestFeed() {
        newton.setVisibility(View.VISIBLE);
        newton.start();
        String url =
                "https://graph.facebook.com/v2.5/PhoneixCSIDTU/posts?access_token=605382859616232|zj6UV0o9j6HhSYY9E2tXHC40iP4&fields=message%2Cobject_id%2Ccreated_time%2Cpicture&__mref=message_bubble";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.v(this.getClass().getSimpleName(), response.toString());
                try {
                    JSONArray array = response.getJSONArray("data");
                    for(int i = 0 ; i < array.length() ; i++) {
                        JSONObject object = array.getJSONObject(i);
                        if(object.has("message")) {
                            newsLines.add(object.getString("message"));
                            newsDates.add(object.getString("created_time"));
                            if(object.has("picture"))
                                newsPhotos.add(object.getString("picture"));
                            else
                                newsPhotos.add("");
                        }
                    }
                    CustomAdapter adapter = new CustomAdapter(newsPhotos, newsLines, newsDates);
                    ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(adapter);
                    animationAdapter.setDuration(1000);
                    news.setAdapter(animationAdapter);
                    newton.stop();
                    newton.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                    newton.stop();
                    newton.setVisibility(View.INVISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        MySingleton.getInstance(getContext()).addToRequestQueue(request);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
