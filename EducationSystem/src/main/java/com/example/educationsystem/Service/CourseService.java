package com.example.educationsystem.Service;

import com.example.educationsystem.Model.Course;
import com.example.educationsystem.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    ArrayList<Course> courses= new ArrayList<>();
    public void addCourse(Course course){
        courses.add(course);
    }
    public ArrayList<Course> getCourses(){
        return courses;
    }

    public boolean deleteCourse(String courseSection){
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseSection().equals(courseSection)){
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateCourse(String courseSection, Course course){
        for (int i = 0; i <courses.size() ; i++) {
            if (courses.get(i).getCourseSection().equals(courseSection)){
                courses.set(i,course);
                return true;
            }
        }
        return false;
    }
}
