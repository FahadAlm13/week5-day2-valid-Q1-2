package spring.boot.week5day2validex2.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week5day2validex2.Api.ApiEvent;
import spring.boot.week5day2validex2.Model.Event;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    //Q3
    ArrayList<Event> events = new ArrayList<>();

    //get
    @GetMapping("/get")
    public ArrayList<Event> getEvents() {
        return events;
    }

    //Post
    @PostMapping("/add")
    public ResponseEntity addEvent(@Valid @RequestBody Event event, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        events.add(event);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiEvent("Success add new event"));
    }

    //update
    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@Valid @RequestBody Event event, @PathVariable int index, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        this.events.set(index, event);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiEvent("Success to update event"));
    }

    //delete
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {
            this.events.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiEvent("Success event delete"));
    }

    //Change capacity
    @PutMapping("/capacity/{index}")
    public ResponseEntity changeCapacity(@RequestParam int capacity, @PathVariable int index) {
            ArrayList<Event> events = this.events;
            Event event = events.get(index);
            event.setCapacity(capacity);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEvent("Success change capacity"));
    }

    //Search for a event by given id
    @GetMapping("/search/{id}")
    public ResponseEntity getEventById(@PathVariable int id) {

            for (Event event : this.events) {
                if (event.getId() == id) {
                    return ResponseEntity.status(HttpStatus.OK).body(event);
                }
            }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiEvent(" Id not found "));
    }
}
