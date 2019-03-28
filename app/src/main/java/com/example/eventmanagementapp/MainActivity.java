package com.example.eventmanagementapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements EventListFragment.AddNewEventListener {
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        EventListFragment eventListFragment = new EventListFragment();
        ft.add(R.id.fragmentContainer, eventListFragment);
        ft.commit();
    }

    @Override
    public void onAddNewEvent() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        AddEventFragment addEventFragment = new AddEventFragment();
        ft.replace(R.id.fragmentContainer, addEventFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onComplete() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        EventListFragment eventListFragment = new EventListFragment();
        ft.replace(R.id.fragmentContainer, eventListFragment);
        ft.commit();
    }
}
