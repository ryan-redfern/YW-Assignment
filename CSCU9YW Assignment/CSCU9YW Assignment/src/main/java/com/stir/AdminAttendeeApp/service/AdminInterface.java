

package com.stir.AdminAttendeeApp.service;

import com.stir.AdminAttendeeApp.model.Attendee;
import com.stir.AdminAttendeeApp.model.Events;
import java.util.List;


public interface AdminInterface
{
    void addEvent(Events event);
    void addAttendee(Attendee attendee);
    Events getEvent(String id);
    String getAttendeeEvents(String attendeeID);
    List<Events> getAllEvents();
    String getAttendeeID(String id);
    void deleteAttendeeEvent(Events event, Attendee attendee);
    void addAttendeeToEvent(Events event, Attendee attendee);
    String getEventAttendees(String eventID);
    String validate(); 
}
