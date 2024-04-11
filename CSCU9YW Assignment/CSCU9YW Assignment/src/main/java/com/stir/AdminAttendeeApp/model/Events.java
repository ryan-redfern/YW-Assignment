// A resource, living in the model sub-package.

package com.stir.AdminAttendeeApp.model;

import java.util.List;

public class Events
{
    private String eventID;
    private String description;
    private String location;
    private String date;
    private String time;
    private String duration;
    private String maxcap;
    
    private int attendeeSize;
    // Standard constructor
    public Events(String eventID, String description, String location, String date, String time, String duration, String maxcap) {
        this.eventID = eventID;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.maxcap = maxcap;
    }
    // Copy constructor
    public Events(Events that) {
        this.eventID = that.eventID;
        this.description = that.description;
        this.location = that.location;
        this.date = that.date;
        this.time = that.time;
        this.duration = that.duration;
        this.maxcap = that.maxcap;
    }
    // Copy constructor
    public Events(Events that,int attendeeSize) {
        this.eventID = that.eventID;
        this.description = that.description;
        this.location = that.location;
        this.date = that.date;
        this.time = that.time;
        this.duration = that.duration;
        this.maxcap = that.maxcap;
        
        this.attendeeSize = attendeeSize;
        
    }
    public String geteventID() { return eventID; }
    public void seteventID(String eventID) { this.eventID = eventID; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTime() { return time; }
    public void getTime(String time) { this.time = time; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getMaxcap() { return maxcap; }
    public void setMaxcap(String maxcap) { this.maxcap = maxcap; }
    
    public void setattendeeSize(int attendeeSize){
        
        this.attendeeSize = attendeeSize;
    }
    public int getAttendeeSize() { return attendeeSize; }
    
    @Override
    public String toString() {
        return "Event{" +
               "eventid=\"" + eventID + "\"," +
               "description=\"" + description  + "\"," +
               "location=\"" + location + "\"," +
               "date=\"" + date + "\"," +
               "time=\"" + time + "\"," +
               "duration=\"" + duration + "\"," +
               "maxcap=\"" + maxcap + "\"}";
               
    }
    
}
