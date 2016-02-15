package com.dtu.csi.csi_dtu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.CustomViewHolder>{
    private static MyClickListener myClickListener;
    ArrayList<EventItem> eventItems;
    //ParallaxViewController parallaxViewController = new ParallaxViewController();
    public EventsAdapter (ArrayList<EventItem> eventItems){
        this.eventItems = eventItems;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
      //  parallaxViewController.registerImageParallax(recyclerView);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        //parallaxViewController.imageParallax(viewHolder.background);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.eventName.setText(eventItems.get(position).event);
        //holder.thumbnail.setImageResource(eventItems.get(position).thumbnail);
        holder.background.setImageResource(eventItems.get(position).header);
    }

    @Override
    public int getItemCount() {
        return eventItems.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView background;
        TextView eventName;
        public CustomViewHolder(View itemView) {
            super(itemView);
            background = (ImageView) itemView.findViewById(R.id.item_header);
            eventName = (TextView) itemView.findViewById(R.id.event_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
    public void setOnItemClickListener(MyClickListener mClickListener) {
        myClickListener = mClickListener;
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
