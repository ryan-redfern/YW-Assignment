// A resource, living in the model sub-package.

package com.stir.AdminAttendeeApp.model;

import java.util.Arrays;


public class Attendee
{
    private String uid;
    private String name;
    private String[] interests;
    

    
    // Standard constructor
    public Attendee(String uid, String name, String[] interests) {
        this.uid = uid;
        this.name = name;
        this.interests = interests;
    }
    // copy constructor
    public Attendee(Attendee that) {
        this.uid = that.uid;
        this.name = that.name;
        this.interests = that.interests;
        
    }

    

    public String getuid() { return uid; }
    public void setuid(String uid) { this.uid = uid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getTopics(){ 
        StringBuffer sb = new StringBuffer();
      for(int i = 0; i < interests.length; i++) {
         sb.append(interests[i]);
      }
      String str = Arrays.toString(interests); 
      return str;
    }
    public void setTopics(String[] interests) { this.interests = interests; }
    
    

    @Override
    public String toString() {
        return "Attendee{" +
               "uid=\"" + uid + "\"," + 
               "name=\"" + name + "\"," +
               "interests=\"" + getTopics() + "\"}";
    }
}
