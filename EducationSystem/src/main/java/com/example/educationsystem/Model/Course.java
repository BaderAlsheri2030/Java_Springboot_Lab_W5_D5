package com.example.educationsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    @NotNull(message = "course name cannot be null")
    private String courseName;
    @NotNull(message = "professor name cannot be null")
    private String professorName;
    @NotNull(message = "course section cannot be null")
    @Pattern(regexp = "\\d+", message = "course section must only be numbers")
    private String courseSection;
    @NotNull(message = "course hours cannot be null")
    @Positive(message = "hours must be in positive")
    private int hours;
    private int grade;

}
