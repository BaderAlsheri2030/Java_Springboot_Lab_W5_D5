package com.example.educationsystem.Controller;

import com.example.educationsystem.Model.Course;
import com.example.educationsystem.Service.CourseService;
import com.example.educationsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/course")
@RequiredArgsConstructor
public class CourserController {

private final CourseService service;

@PostMapping("/add")
public ResponseEntity addCourse(@Valid @RequestBody Course course, Errors errors) {
    if (errors.hasErrors()) {
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    service.addCourse(course);
    return ResponseEntity.status(HttpStatus.OK).body("Course added");
}

@GetMapping("/get")
public ResponseEntity getCourses(){
    return ResponseEntity.status(HttpStatus.OK).body(service.getCourses());
}
@DeleteMapping("/delete/{courseSection}")
public ResponseEntity deleteCourse(@PathVariable String courseSection){
    boolean isDeleted = service.deleteCourse(courseSection);
    if (isDeleted){
        return ResponseEntity.status(HttpStatus.OK).body("Course with section number: "+courseSection+" is deleted");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Course Section or Course wasn't found");
}

@PutMapping("/update/{courseSection}")
public ResponseEntity updateCourse(@PathVariable String courseSection,@Valid @RequestBody Course course, Errors errors){
    if (errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    boolean isUpdated = service.updateCourse(courseSection,course);
    if (isUpdated){
        return ResponseEntity.status(HttpStatus.OK).body("Course is updated");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course section is invalid or doesn't exist");
}








}
