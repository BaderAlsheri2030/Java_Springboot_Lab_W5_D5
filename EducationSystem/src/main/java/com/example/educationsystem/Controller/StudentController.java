package com.example.educationsystem.Controller;

import com.example.educationsystem.Model.Course;
import com.example.educationsystem.Model.Student;
import com.example.educationsystem.Service.CourseService;
import com.example.educationsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;



    @GetMapping("/get")
    public ResponseEntity getStudents(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getStudents());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent( @RequestBody Student student, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        service.addStudent(student);
        return ResponseEntity.status(HttpStatus.OK).body("Student added");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id){
        boolean isDeleted = service.deleteStudent(id);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("Student with ID: "+id+" is deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student doesn't exist or id invalid");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id,@RequestBody Student student,Errors errors ){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isUpdated = service.updateStudent(id, student);
        if (isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body("Student with ID:"+id+" is updated ");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid id or student doesn't exist");
    }



    @GetMapping("/get/courses")
    public ResponseEntity addCoursesToStudentsPage(){
        return ResponseEntity.status(HttpStatus.OK).body(service.addCoursesToStudentsPage());

    }

    @PutMapping("/add/course/{id}/{courseSection}")
    public ResponseEntity addCourseToStudent(@PathVariable String id ,@PathVariable String courseSection){
        boolean isAdded =service.addCourseToStudent(id,courseSection);
        if (isAdded) {
            return ResponseEntity.status(HttpStatus.OK).body("Course section:"+courseSection+" added to student with ID:"+id);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid ID or Course Section or student hours are more than 18, Course may not exist");
    }
    @DeleteMapping("/delete/course/{id}/{courseSection}")
    public ResponseEntity deleteStudentCourse(@PathVariable String id ,@PathVariable String courseSection){
        boolean isDeleted =service.deleteStudentCourse(id,courseSection);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Course section:"+courseSection+" removed from student with ID:"+id);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid ID or Course Section, or Course may not exist in the student plan");
    }

    @PutMapping("/grade/{id}/{courseSection}/{grade}")
    public ResponseEntity assignMarkToStudent(@PathVariable String id,@PathVariable String courseSection,@PathVariable int grade) {
        boolean isGraded = service.assignMarkToStudent(id, courseSection, grade);
        if (isGraded){
            return ResponseEntity.status(HttpStatus.OK).body("Student course is graded");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid id or course section (grade cannot be less than zero or more than 100)");
    }



}
