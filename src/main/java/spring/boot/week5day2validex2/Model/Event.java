package spring.boot.week5day2validex2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {
    @NotNull
    @Min(3)
    private int id;
    @NotEmpty
    @Size(min = 2,message = "Description can't be empty and must be greater than 15")
    private String description;
    @NotNull
    @Min(25)
    @Positive
    private int capacity;

@FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
@FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
