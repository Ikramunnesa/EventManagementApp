package com.example.eventmanagementapp;

import java.util.ArrayList;
import java.util.List;

public class  Event {
    private int eventID;
    private String eventName;
    private String eventCategory;
    private String eventLocation;
    private String eventDate;

    public Event(int eventID, String eventName, String eventCategory, String eventLocation, String eventDate) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventCategory = eventCategory;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
    }

    public Event(String eventName, String eventCategory, String eventLocation, String eventDate) {
        this.eventName = eventName;
        this.eventCategory = eventCategory;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
    }

    public int getEventID() {
        return eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventDate() {
        return eventDate;
    }

    public static List<Event>generateEventList(){
        List<Event> eventList = new ArrayList<>();
            eventList.add(new Event(R.drawable.convocation, "Convocation", "versity", "Banani", "08/05/2019"));
            eventList.add(new Event(R.drawable.festival, "Festival", "festival", "Bashundhara", "21/03/2018"));
            eventList.add(new Event(R.drawable.job_fair, "Job Fair", "carrer", "Dhanmondi", "12/06/2019"));
            eventList.add(new Event(R.drawable.math_olympiad, "Math Olympiad", "olympiad", "Karawn Bazar", "25/02/2017"));
            eventList.add(new Event(R.drawable.pro_contest, "Programming Contest", "programming", "Gulshan", "03/09/2016"));
            eventList.add(new Event(R.drawable.reunion, "Reunion", "versity", "Sahabag", "05/10/2013"));
            eventList.add(new Event(R.drawable.robotics, "Robotics Contest", "robotics", "Baridhara", "31/03/2019"));
            eventList.add(new Event(R.drawable.science_olympiad, "Science Olympiad", "olympiad", "Panthopath", "07/12/2015"));
            return eventList;
    }
}
