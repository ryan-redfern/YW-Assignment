

package com.stir.AdminAttendeeApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@CrossOrigin
public class AdminController
{
    
    private AdminInterface admin;
    
    

   /**
    * Constructor - uses a reference to the admin interface
    */
    public AdminController(AdminInterface admin) {
        this.admin = admin;
        
        
    }
    /**
    * Gets all the events
    */
    @GetMapping("/admin/fetchall")
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
    * Gets all attendees for an event
    */
    @GetMapping("/admin/fetchattendees/{eventID}")
    public String getEventAttendees(@PathVariable String eventID) {
        String attendeeString = admin.getEventAttendees(eventID);
        if (attendeeString.isEmpty()){
            System.out.println("GET - 404");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        System.out.println("GET - 200");
        return attendeeString;
    }
    /**
    * Validates the users in the hashmap databases
    */
    @GetMapping("/admin/validate")
    public String validate() {
        String validationString = admin.validate();
        if (validationString.isEmpty()){
            System.out.println("GET - 404");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        System.out.println("GET - 200");
        return validationString;
    }
    /**
    * Used to create an event in the system
    */
    @PostMapping("/admin/createevent/{eventid}/{name}/{description}/{location}/{date}/{time}/{duration}")
    public ResponseEntity<Void> createEvent(@PathVariable String eventid, @PathVariable String name,@PathVariable String description, @PathVariable String location, @PathVariable String date, @PathVariable String time, @PathVariable String duration ) throws JsonProcessingException {
    
        Events event = new Events(eventid, name, description, location, date, time, duration);
        
        
        admin.addEvent(event);
        System.out.println("POST - 201");
        return new ResponseEntity(HttpStatus.CREATED);
    }
    

    
}
