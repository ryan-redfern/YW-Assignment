

package com.stir.AdminAttendeeApp.controller;


import com.stir.AdminAttendeeApp.model.Events;

import com.stir.AdminAttendeeApp.service.AdminInterface;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.stir.AdminAttendeeApp.model.Attendee;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@CrossOrigin
public class AttendeeController
{
    
   
    
    private AdminInterface admin;

    /**
    * Constructor - keeps a reference to the admin interface
    */
    public AttendeeController(AdminInterface admin) {
        
        
        this.admin = admin;
    }
    /**
    * Used to get events
    */
    @GetMapping("/attendee/fetchall")
    public List<Events> getAllEvents() {
        List<Events> eventList = admin.getAllEvents();
        if (eventList.isEmpty()){
            System.out.println("GET - 404");
             throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        System.out.println("GET - 200");
        return eventList;
    }
    /**
    * Get events based off the attendee ID
    */
    @GetMapping("/attendee/fetchall/{attendeeID}")
    public String getAttendeeEvents(@PathVariable String attendeeID) {
        String attendeeEventString = admin.getAttendeeEvents(attendeeID);
        if (attendeeEventString.isEmpty()){
            System.out.println("GET - 404");
             throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        System.out.println("GET - 200");
        
        
        return attendeeEventString;
    }
    /**
    * Used to register the user to an event
    */
    @PostMapping("/attendee/register/{eventID}/{attendeeID}")
    public ResponseEntity<Void> register(@PathVariable String eventID, @PathVariable String attendeeID) throws JsonProcessingException {
        Events event = admin.getEvent(eventID);
        if (event == null){
            System.out.println("POST - 404");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            
        }
        String attendeeinfo = admin.getAttendeeID(attendeeID);
        if (attendeeinfo.equals("Error")){
            System.out.println("POST - 404");
            
             throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
            
        
        String s1 = attendeeinfo.substring(attendeeinfo.indexOf(",")+1);
        s1 = s1.trim();
        String s2 = s1.substring(s1.indexOf(":")+1);
        s2 = s2.trim();
        String s3 = s2.substring(0, s2.length() - 1);
        Gson gson= new Gson();
        Attendee attendee = gson.fromJson(s3,Attendee.class);
        admin.addAttendee(attendee);
        admin.addAttendeeToEvent(event,attendee);
        System.out.println("POST - 201");
        return new ResponseEntity(HttpStatus.CREATED);
    }
    /**
    * Method just for testing the validation method
    */
    public void registerforvalidationtesting(String eventID, String attendeeID, String name, String[] interests){
        Events event = admin.getEvent(eventID);
        Attendee attendee = new Attendee(attendeeID, name, interests);
        admin.addAttendee(attendee);
        admin.addAttendeeToEvent(event, attendee);
        
        
    }
    /**
    * Deletes a user from an event
    */
    @DeleteMapping("/attendee/delete/{eventID}/{attendeeID}")
    public ResponseEntity<Void> deleteAttendeeEvent(@PathVariable String eventID, @PathVariable String attendeeID) {
        
        Events event = admin.getEvent(eventID);
        if (event == null){
            System.out.println("DELETE - 404");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            
        }
        String attendeeinfo = admin.getAttendeeID(attendeeID);
        if (attendeeinfo.equals("Error")){
            System.out.println("DELETE - 404");
            
             throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        String s1 = attendeeinfo.substring(attendeeinfo.indexOf(",")+1);
        s1 = s1.trim();
        String s2 = s1.substring(s1.indexOf(":")+1);
        s2 = s2.trim();
        String s3 = s2.substring(0, s2.length() - 1);
        Gson gson= new Gson();
        Attendee attendee = gson.fromJson(s3,Attendee.class);
        System.out.println("DELETE - 200");
        admin.deleteAttendeeEvent(event, attendee);
        

    return new ResponseEntity(HttpStatus.OK);
    }



    
    
    
    
    
    

    
}
