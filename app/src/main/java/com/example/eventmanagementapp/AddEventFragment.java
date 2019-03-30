package com.example.eventmanagementapp;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventmanagementapp.eventdb.EventDataSource;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddEventFragment extends Fragment {
    private EditText nameET, categoryET, locationET, dateET;
    private Button saveBtn, updateBtn;
    private EventListFragment.AddNewEventListener listener;
    private int eventID = 0;
    private EventDataSource dataSource;
    private EventAdapter.EditEventListener editEventListener;

    public AddEventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (EventListFragment.AddNewEventListener) context;
        dataSource = new EventDataSource(context);
        editEventListener = (EventAdapter.EditEventListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameET = view.findViewById(R.id.nameInput);
        categoryET = view.findViewById(R.id.categoryInput);
        locationET = view.findViewById(R.id.locationInput);
        dateET = view.findViewById(R.id.dateInput);
        saveBtn = view.findViewById(R.id.saveBtn);
        updateBtn = view.findViewById(R.id.updateBtn);

        try {
            eventID = getArguments().getInt("id");
            saveBtn.setVisibility(View.GONE);
            updateBtn.setVisibility(View.VISIBLE);
            final EventDataSource dataSource = new EventDataSource(getActivity());
            Event event = dataSource.getEventById(eventID);
            if(event != null){
                nameET.setText(event.getEventName());
                categoryET.setText(event.getEventCategory());
                locationET.setText(event.getEventLocation());
                dateET.setText(event.getEventDate());
            }
            Toast.makeText(getActivity(), ""+eventID, Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameET.getText().toString();
                String category = categoryET.getText().toString();
                String location = locationET.getText().toString();
                String date = dateET .getText().toString();

                Event event = new Event(name, category, location, date);
                final EventDataSource dataSource = new EventDataSource(getActivity());
                final long insertedRow = dataSource.insertNewEvent(event);
                if(insertedRow > 0){
                    Toast.makeText(getActivity(),"saved", Toast.LENGTH_SHORT).show();
                    listener.onComplete();
                }else {
                    Toast.makeText(getActivity(),"failed to saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String category = categoryET.getText().toString();
                String location = locationET.getText().toString();
                String date = dateET .getText().toString();

                final Event event = new Event(eventID, name, category, location, date);
                final int updatedRow = dataSource.updateEventById(event);
                if(updatedRow > 0){
                    Toast.makeText(getActivity(),"updated", Toast.LENGTH_SHORT).show();
                    editEventListener.onEditComplete();
                }
            }
        });
    }
}
