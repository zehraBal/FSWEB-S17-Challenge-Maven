package com.workintech.spring17challenge.controller;

import com.workintech.spring17challenge.entity.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    public List<Course> courses;
    private LowCourseGpa lowCourseGpa;
    private MediumCourseGpa mediumCourseGpa;
    private HighCourseGpa highCourseGpa;

    @PostConstruct
    public void init(){
        courses=new ArrayList<>();
    }

    @Autowired
    public CourseController(LowCourseGpa lowCourseGpa,MediumCourseGpa mediumCourseGpa, HighCourseGpa highCourseGpa){
        this.highCourseGpa=highCourseGpa;
        this.mediumCourseGpa=mediumCourseGpa;
        this.lowCourseGpa=lowCourseGpa;
    }

    @GetMapping
    public List<Course> getAllCourses(){
        return  courses;
    }

    @GetMapping("/{name}")
    public  Course getCourseByName(@PathVariable String name){
        for(Course c : courses){
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResult saveCourse(@RequestBody Course course){
        if(!courses.contains(course)){
            courses.add(course);
          int totalGpa= TotalGpaCalculator.calculateTotalGPA(course);
            return new CourseResult(totalGpa,course);
        }
        return null;
    }

    @PutMapping("/{id}")
    public CourseResult updateCourse(@PathVariable int id,@RequestBody Course course) {
        courses.removeIf(c -> c.getId() == id);
        courses.add(new Course(id, course.getName(), course.getCredit(), course.getGrade()));
        return new CourseResult(TotalGpaCalculator.calculateTotalGPA(course), course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id){
        courses.removeIf(c -> c.getId() == id);
    }
}
