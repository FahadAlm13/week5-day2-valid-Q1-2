package spring.boot.week5day2validex.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tracker {

    @NotNull
    @Min(3)
    private int id;
    @NotEmpty
    @Size(min = 9, message = "Title can't be empty and must be greater than 8 ")
    private String title;

    @NotEmpty
    @Size(min = 15, message = "Description can't be empty and must be greater than 15")
    private String description;

    @NotEmpty
    @Pattern(regexp = "not started|in progress|completed", message = "Status must be 'Not Started' or 'In Progress' or 'Completed'")
    private String status;
    @NotEmpty
    @Size(min = 7, message = "company name can't be empty and must be greater than 6 ")
    private String companyName;
}
