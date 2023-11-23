package com.example.educationsystem.Service;


import com.example.educationsystem.Model.Course;
import com.example.educationsystem.Model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final CourseService courseService;
    ArrayList<Student> students =new ArrayList<>();
    ArrayList<Course> course = new ArrayList<>();
    public ArrayList<Student> getStudents(){
        return students;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public boolean deleteStudent(String id){
        for (int i = 0; i <students.size() ; i++) {

        if (students.get(i).getId().equals(id)){
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateStudent(String id , Student student){
        for (int i = 0; i <students.size(); i++) {
            if (students.get(i).getId().equals(id)){
                students.set(i,student);
                return true;
            }
        }
        return false;
    }
    public ArrayList<Course> addCoursesToStudentsPage(){
     ArrayList<Course> courses = courseService.getCourses();
        course.clear();
        course.addAll(courses);
        return course;
    }

    public boolean addCourseToStudent(String id,String section){
        for (Student student:students) {
            if (student.getId().equals(id)){
                if (student.getCourseHours() <= 18){
                for (Course c: course) {
                    if (c.getCourseSection().equals(section)){
                            student.setCourseHours(student.getCourseHours()+c.getHours());
                            student.getCourses().add(c);
                            return true;

                    }
                }
            }
                else return false;
        }
        }
        return false;
    }

    public boolean deleteStudentCourse(String id, String section){
        for (Student student:students) {
            if (student.getId().equals(id)){
                    for (Course c: course) {
                        if (c.getCourseSection().equals(section)){
                            student.setCourseHours(student.getCourseHours()-c.getHours());
                            student.getCourses().remove(c);
                            return true;

                        }
                }
            }
        }
        return false;

    }
    public boolean assignMarkToStudent(String id, String section, int grade) {
        if (grade < 100 && grade >0){
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                ArrayList<Course> courses = students.get(i).getCourses();
                for (int j = 0; j < courses.size(); j++) {
                    if (courses.get(j).getCourseSection().equals(section)) {
                        courses.get(j).setGrade(grade);
                        return true;
                    }
                }
            }
        }
        }else return false;
        return false;
    }

}

