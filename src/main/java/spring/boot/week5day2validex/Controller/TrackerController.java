package spring.boot.week5day2validex.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week5day2validex.Api.ApiTracker;
import spring.boot.week5day2validex.Model.Tracker;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tracker")
public class TrackerController {

    //Q2
    ArrayList<Tracker> trackers = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Tracker> getTrackers() {
        return trackers;
    }

    //Post
    @PostMapping("add")
    public ResponseEntity addTracker(@Valid @RequestBody Tracker tracker, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        this.trackers.add(tracker);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiTracker("Success add new tracker"));
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity updateTracker(@PathVariable int id, @Valid @RequestBody Tracker tracker, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        this.trackers.set(id, tracker);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiTracker("Success tasks update"));
    }

    //delete
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteTracker(@PathVariable int index) {
        trackers.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiTracker("Success tasks delete"));
    }

    //Change the project status as done or not done
    @GetMapping("/status/{index}")
    public ResponseEntity changeStatus(@PathVariable int index) {
        if (trackers.get(index).getStatus().equalsIgnoreCase("Not started")) {
            trackers.get(index).setStatus("in progress");
        } else if (trackers.get(index).getStatus().equalsIgnoreCase("in progress")) {
            trackers.get(index).setStatus("completed");
        } else if (trackers.get(index).getStatus().equalsIgnoreCase("completed")) {
            trackers.get(index).getStatus();
        }
        return ResponseEntity.status(HttpStatus.OK).body(trackers.get(index).getStatus());
    }

    //Search for a project by given title
    @GetMapping("/search/{title}")
    public ResponseEntity searchTracker(@PathVariable String title) {
        for (Tracker tracker : trackers) {
            if (tracker.getTitle().equals(title)) {
                return ResponseEntity.status(HttpStatus.OK).body(tracker);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiTracker("We do not found the title you lock for "));
    }

    //• Display All project for one company by companyName.
    @GetMapping("getTrackersByCompanyName/{companyName}")
    public ResponseEntity getTrackersByCompanyName(@PathVariable String companyName) {
        ArrayList<Tracker> search = new ArrayList<>();
        for (Tracker tracker : trackers) {
            if (tracker.getCompanyName().equals(companyName)) {
                search.add(tracker);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(search);
    }
}
