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

import com.example.eventmanagementapp.eventdb.EventDateSource;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddEventFragment extends Fragment {
    private EditText nameET, categoryET, locationET, dateET;
    private Button saveBtn;
    private EventListFragment.AddNewEventListener listener;

    public AddEventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (EventListFragment.AddNewEventListener) context;
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

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameET.getText().toString();
                String category = categoryET.getText().toString();
                String location = locationET.getText().toString();
                String date = dateET .getText().toString();

                Event event = new Event(name, category, location, date);
                final EventDateSource dateSource = new EventDateSource(getActivity());
                final long insertedRow = dateSource.insertNewEvent(event);
                if(insertedRow > 0){
                    Toast.makeText(getActivity(),"saved", Toast.LENGTH_SHORT).show();
                    listener.onComplete();
                }else {
                    Toast.makeText(getActivity(),"failed to saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
