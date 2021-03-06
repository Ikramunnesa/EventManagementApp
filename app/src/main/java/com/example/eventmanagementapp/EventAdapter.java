package com.example.eventmanagementapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventmanagementapp.eventdb.EventDataSource;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> eventList;
    private Context context;
    private EventDataSource dataSource;
    private EditEventListener editEventListener;

    public EventAdapter(Context context, List<Event> eventList) {
        this.eventList = eventList;
        this.context = context;
        dataSource = new EventDataSource(context);
        editEventListener = (EditEventListener) context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_row, viewGroup, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, final int i) {
        final Event event = eventList.get(i);
        //holder.imageView.setImageResource(event.getImage());
        holder.nameTV.setText(event.getEventName());
        holder.locationTV.setText(event.getEventLocation());
        holder.dateTV.setText(event.getEventDate());
        holder.menuTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.event_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.item_details:
                                Toast.makeText(context, event.getEventName(), Toast.LENGTH_SHORT).show();

                                break;
                            case R.id.item_delete:
                                final int deletedRow = dataSource.deleteEventById(event.getEventID());
                                if(deletedRow > 0){
                                    Toast.makeText(context,"deleted", Toast.LENGTH_SHORT).show();
                                    eventList.remove(i);
                                    notifyDataSetChanged();
                                }
                                else {
                                    Toast.makeText(context,"failed to delete", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case R.id.item_edit:
                                editEventListener.onEditBtnClicked(event.getEventID());
                                break;
                        }
                        return false;
                    }
                });
            }
        });

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

    interface EditEventListener{
        void onEditBtnClicked(int eventID);
        void onEditComplete();
    }
}
