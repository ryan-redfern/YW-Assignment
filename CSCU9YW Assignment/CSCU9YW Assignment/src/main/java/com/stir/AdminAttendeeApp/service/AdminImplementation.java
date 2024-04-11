

package com.stir.AdminAttendeeApp.service;
import com.google.gson.Gson;
import com.stir.AdminAttendeeApp.model.Events;
import com.stir.AdminAttendeeApp.model.Attendee;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;


@Component
public class AdminImplementation implements AdminInterface
{
    private final Map<String, Events> eventInfoDB = new HashMap();
    private final Map<String, Attendee> attendeeInfoDB = new HashMap();
    private final Map<String, List<Events>> eventsDB = new HashMap();
    private final Map<String, List<Attendee>> attendeeDB = new HashMap();
    
    /**
    * Default Constructor
    */
    public AdminImplementation() {  
    }
    /**
    * Method to store events into the two relevant hashmap storages
    */
    public void addEvent(Events event) {
        List<Attendee> newList = new ArrayList<>();
        if (attendeeDB.containsKey(event.geteventID())){
            Events b = eventInfoDB.get(event.geteventID());
            event.setattendeeSize(b.getAttendeeSize());
            
            eventInfoDB.put(event.geteventID(), event);
            updateEventInfo(event);
            
        } else {
        attendeeDB.put(event.geteventID(), newList);
        eventInfoDB.put(event.geteventID(), event);
        
        }
    }
    /**
    * Updates the information about the events
    */
    public void updateEventInfo(Events event){
        
        for (Map.Entry<String, List<Events>> entry : eventsDB.entrySet()) 
        {     
            
            List<Events> newList = new ArrayList <Events>();
            newList = entry.getValue();
            
            int i = 0;
            while (i < newList.size()){
               
                if (newList.get(i).geteventID().equals(event.geteventID())){
                    
                    newList.set(i, event);
                    eventsDB.put(entry.getKey(), newList);
  
                    
                }
                i = i + 1;
            }
        }

            }
    /**
    * Adds attendee to both relevant hashmap storages
    */
    public void addAttendee(Attendee attendee) {
        List<Events> newList = new ArrayList <>();
        if (eventsDB.containsKey(attendee.getuid())){
            System.out.println("Already in hashmap");
        } else {
        eventsDB.put(attendee.getuid(), newList);
        attendeeInfoDB.put(attendee.getuid(), attendee);

        }
    }
   /**
    * Deletes an event for an attendee
    */
    public void deleteAttendeeEvent(Events event, Attendee attendee){
        Iterator<Map.Entry<String, List<Events>> >
            iterator = eventsDB.entrySet().iterator();
        while (iterator.hasNext()) {
            // Get the entry at this iteration
            Map.Entry<String, List<Events>>
                entry
                = iterator.next();
            // Check if this value is the required value
            if (entry.getKey().equals(attendee.getuid())) {
                List<Events> oldList = (List<Events>) eventsDB.get(attendee.getuid());
                int i = 0;
                while (i < oldList.size()){
                    if (oldList.get(i).geteventID().equals(event.geteventID())){
                        oldList.remove(i);
                
                    }
                    i= i + 1;
                }
                List<Attendee> oldList1 = (List<Attendee>) attendeeDB.get(event.geteventID());
                int j = 0;
                while (j < oldList1.size()){
                    if (oldList1.get(j).getuid().equals(attendee.getuid())){
                        oldList1.remove(j);
                
                    }
                    j= j + 1;
                }
                attendeeDB.put(event.geteventID(), oldList1);
                eventsDB.put(attendee.getuid(), oldList);
                List<Attendee> oldList2 = (List<Attendee>)attendeeDB.get(event.geteventID());
                int x = (oldList2.size());
                event = new Events(event, x);
                eventInfoDB.put(event.geteventID(), event);
            }
    }
        
    }
    /**
    * Adds attendee to an event
    */
    public void addAttendeeToEvent(Events event, Attendee attendee){
        boolean contains = false;
        boolean contains1;
        if (attendeeDB.isEmpty()){
        System.out.println("empty");
        }
        else{
        List<Attendee> newList = new ArrayList <Attendee>();
        List<Attendee> oldList = (List<Attendee>)attendeeDB.get(event.geteventID());
        if (oldList.isEmpty() ||  oldList == null){
            newList.add(attendee);
            attendeeDB.put(event.geteventID(), newList);
            int x = newList.size();
            event = new Events(event, x);
            eventInfoDB.put(event.geteventID(), event);
        } else {
            contains1 = false;
            int i =0;
            while (i < oldList.size()){
                if (oldList.get(i).getuid().equals(attendee.getuid())){
                    contains1 = true;
                }
                i = i + 1;
            }
            
            if (contains1){
                System.out.println("Already contains");
            } else {
            oldList.add(attendee);
            attendeeDB.put(event.geteventID(), oldList);
            int sizeOfOldList = oldList.size();
            
            if (sizeOfOldList<0){
                sizeOfOldList =0;
            }
            event = new Events(event, sizeOfOldList);
            eventInfoDB.put(event.geteventID(), event);
            }
            
        }
         
        
        }
        ///////////
        if (eventsDB.isEmpty()){
        System.out.println("empty");
        }
        else{
        List<Events> newList = new ArrayList <Events>();
        List<Events> oldList = (List<Events>)eventsDB.get(attendee.getuid());
        if (oldList.isEmpty() ||  oldList == null){
            
            newList.add(event);
            eventsDB.put(attendee.getuid(), newList);
            
        } else {
            contains = false;
            int i =0;
            
            while (i < oldList.size()){
                if (oldList.get(i).geteventID().equals(event.geteventID())){
                
                
                    contains = true;
                    
                }
                i = i + 1;
            }
            
            if (contains){
                System.out.println("Already contains");
            } else {
            oldList.add(event);
            eventsDB.put(attendee.getuid(), oldList);
            }
            
        }
  
        }
            
        }

    
    /**
    * Searches for an event based on it's ID and returns it
    */
    public Events getEvent(String id) {
        Events event = eventInfoDB.get(id);
        if (event == null) {
            return null;
        }
        // copying event to protect objects in the database from changes
        event = new Events(event);
        
        return event;
    }
    
    
    /**
    * Gets the events for an attendee based on the attendeeID
    */
    public String getAttendeeEvents(String attendeeID) {
        Iterator<Map.Entry<String, List<Events>> >
            iterator = eventsDB.entrySet().iterator();
  
        // Iterate over the HashMap
        String a = "";
        while (iterator.hasNext()) {
  
            // Get the entry at this iteration
            Map.Entry<String, List<Events>>
                entry
                = iterator.next();
            
  
            // Check if this value is the required value
            if (entry.getKey().equals(attendeeID)) {
                List<Events> oldList = (List<Events>) eventsDB.get(attendeeID);
            
  
                // add the data to the string
                a = attendeeID +  " : " + oldList.toString();
                
                
            }

        
    }
        return a;
    }
    /**
    * Gets the attendee for an event based on the eventID
    */
    public String getEventAttendees(String eventID) {
        Iterator<Map.Entry<String, List<Attendee>> >
            iterator = attendeeDB.entrySet().iterator();
  
        // Iterate over the HashMap
        String a = "";
        while (iterator.hasNext()) {
            // Get the entry at this iteration
            Map.Entry<String, List<Attendee>>
                entry
                = iterator.next();
            
            // Check if this value is the required value
            if (entry.getKey().equals(eventID)) {
                List<Attendee> oldList = (List<Attendee>) attendeeDB.get(eventID);
           
                // Remove this entry from HashMap
                a = eventID +  " : " + oldList.toString();
         
            }
    
    }
        return a;
    }

   /**
    * Gets all events and returns it as a list
    */
    public List<Events> getAllEvents() {
        List<Events> values = new ArrayList<>(eventInfoDB.values());
        return values;
    
    }
    
        
        
    

    /**
    * Searches the user profile for an ID, returns a JSON string if found 
    */
    public String getAttendeeID(String attendeeID){
        String x = "";
        try {
		URL url = new URL("https://pmaier.eu.pythonanywhere.com/user/" + attendeeID);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		if (conn.getResponseCode() != 200) {
                    return ("Error");
			//throw new RuntimeException("Failed : HTTP error code : "
			//		+ conn.getResponseCode());
                        
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		
		while ((output = br.readLine()) != null) {
			
                        x = output;
		}
                
		conn.disconnect();
                return x;

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }
        return x;

	}
    /**
    * Checks whether the user is in the user profile
    * 
    */
    public String validate(){
        Iterator attendeeInfoDBIterator = attendeeInfoDB.entrySet().iterator();
        List<String> attendeeIDs = new ArrayList<>();
        List<String> validateString = new ArrayList<>();
        while (attendeeInfoDBIterator.hasNext()) {
            Map.Entry mapElement
                = (Map.Entry)attendeeInfoDBIterator.next();
            attendeeIDs.add(mapElement.getKey().toString());
            
        }
        int i = 0;
        while (i < attendeeIDs.size()){
        String id = attendeeIDs.get(i);
        String a = getAttendeeID(id);
        if (a.equals("Error")){
            attendeeInfoDB.remove(attendeeIDs.get(i));
            eventsDB.remove(attendeeIDs.get(i));
            validateString.add(attendeeIDs.get(i) + ": Removed");
            removeAttendeeInfo(attendeeIDs.get(i));
        } else {
        String s1 = a.substring(a.indexOf(",")+1);
        s1 = s1.trim();
        String s2 = s1.substring(s1.indexOf(":")+1);
        s2 = s2.trim();
        String s3 = s2.substring(0, s2.length() - 1);
        Gson gson= new Gson();
        Attendee attendee = gson.fromJson(s3,Attendee.class);
        attendeeInfoDB.put(attendee.getuid(), attendee);
        updateAttendeeInfo(attendee);
        
        validateString.add(attendee.getuid() + ": Validated");
       
        }
        i = i+1;
        }
        return validateString.toString();
    
    }
    /**
    * Updates the attendee information if their information as updated in the user profile
    */
    public void updateAttendeeInfo(Attendee attendee){
        
        for (Map.Entry<String, List<Attendee>> entry : attendeeDB.entrySet()) 
        {     
            List<Attendee> newList = new ArrayList <Attendee>();
            newList = entry.getValue();
            int i = 0;
            while (i < newList.size()){
               
                if (newList.get(i).getuid().equals(attendee.getuid())){
                    newList.set(i, attendee);  
                }
                i = i + 1;
            }
            attendeeDB.put(entry.getKey(), newList);
        }
  
            }
    /**
    * Removes attendee information if their user profile has been deleted
    */
    public void removeAttendeeInfo(String attendeeID){
        
        for (Map.Entry<String, List<Attendee>> entry : attendeeDB.entrySet()) 
        {     
            List<Attendee> newList = new ArrayList <Attendee>();
            newList = entry.getValue();
            int i = 0;
            while (i < newList.size()){
                if (newList.get(i).getuid().equals(attendeeID)){
                    newList.remove(i);
                    entry.getKey();
                    
                    Events b = eventInfoDB.get(entry.getKey());
                    Events event = new Events(b);
                    event.setattendeeSize(b.getAttendeeSize()-1);
                    eventInfoDB.put(event.geteventID(), event);
    
                }
                i = i + 1;
            }
            attendeeDB.put(entry.getKey(), newList);
        }
  
            }
 
    }
        
        
        
    
        
        
    

   
   

   


