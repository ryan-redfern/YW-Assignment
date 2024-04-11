

package com.stir.AdminAttendeeApp;


import com.stir.AdminAttendeeApp.controller.AttendeeController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.context.annotation.Bean;

import com.stir.AdminAttendeeApp.model.*;
import com.stir.AdminAttendeeApp.service.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminAttendeeApp
{
    public static void main(String[] args) {
        SpringApplication.run(AdminAttendeeApp.class, args);
    }

    
    
    @Bean
    public CommandLineRunner initDB(AdminInterface admin, AttendeeController a) {
        return new CommandLineRunner() {
            @Override
            public void run(String[] args) throws Exception {
               
                
                admin.addEvent(new Events("1", "AI Event", "James Bulding", "18/02/2022", "4pm", "2hours", "300"));
                admin.addEvent(new Events("2", "Coding event", "Roger Building", "18/02/2022", "4pm", "2hours", "300"));
               
                
                String[] b = {"TESTINTEREST"};
                
                
                a.registerforvalidationtesting("1", "geXvXZ_PUjo", "Matthew SMITH", b);
                a.registerforvalidationtesting("1", "testAttendeeToBeDeleted", "Test User", b);
                        
       
                
            }
        };
    }
}
