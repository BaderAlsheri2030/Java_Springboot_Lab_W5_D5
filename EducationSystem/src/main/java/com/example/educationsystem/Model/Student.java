package com.example.educationsystem.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student {
    @NotNull(message = "name cannot be null")
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String name;
    @NotNull(message = "id cannot be null")
    @Pattern(regexp = "\\d+", message = "id must only be numbers")
    private String id;
    private ArrayList<Course> courses;
    @NotNull(message = "course hours must not be null")
    @Positive(message = "course hours must not be in minus")
    private int courseHours =0;
    @NotNull(message = "email cannot be null")
    @Email(message = "must be a valid email")
    private String email;

}
