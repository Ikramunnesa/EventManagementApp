package com.example.eventmanagementapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> eventList;

    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_row, viewGroup, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int i) {
        final Event event = eventList.get(i);
        holder.imageView.setImageResource(event.getImage());
        holder.nameTV.setText(event.getEventName());
        holder.locationTV.setText(event.getEventLocation());
        holder.dateTV.setText(event.getEventDate());

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView nameTV, locationTV, dateTV, menuTV;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.row_imageview);
            nameTV = itemView.findViewById(R.id.row_name);
            locationTV = itemView.findViewById(R.id.row_location);
            dateTV = itemView.findViewById(R.id.row_date);
            menuTV = itemView.findViewById(R.id.row_menu);
        }
    }
}
