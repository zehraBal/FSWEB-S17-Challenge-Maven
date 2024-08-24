package com.workintech.spring17challenge.controller;

import com.workintech.spring17challenge.entity.*;
import com.workintech.spring17challenge.exceptions.ApiException;
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
//        if(name == null){
//            throw new ApiException("Name musn't be null",HttpStatus.BAD_REQUEST);
//        }

        for(Course c : courses){
            if(c.getName().equals(name)){
                return c;
            }
            else{
                throw new ApiException("The course you are looking for with this name doesn't exist",HttpStatus.NOT_FOUND);
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResult saveCourse(@RequestBody Course course){
        if(course.getCredit()<0 || course.getCredit()>4){
            throw new ApiException("Credit must be between 0-4",HttpStatus.BAD_REQUEST);
        }
//        for(Course c:courses){
//            if(c.getName().equals(course.getName())){
//                throw new ApiException("Name must be unique",HttpStatus.BAD_REQUEST);
//            }
//        }
        if(!courses.contains(course)) {
            courses.add(course);
            int totalGpa = TotalGpaCalculator.calculateTotalGPA(course);
            return new CourseResult(totalGpa, course);
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResult updateCourse(@PathVariable int id,@RequestBody Course course) {
        if(id<=0){
            throw new ApiException("ID must be greater than 0",HttpStatus.BAD_REQUEST);
        }
        for(Course c:courses){
            if(c.getId()==id){
                courses.remove(c);
                courses.add(new Course(id, course.getName(), course.getCredit(), course.getGrade()));
                return new CourseResult(TotalGpaCalculator.calculateTotalGPA(course), course);

            }else{
                throw new ApiException("Course you are looking for with this ID doesn't exist",HttpStatus.NOT_FOUND);
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteCourse(@PathVariable int id){
        if(id<=0){
            throw new ApiException("ID must be greater than 0",HttpStatus.BAD_REQUEST);
        }
        courses.removeIf(c -> c.getId() == id);
    }
}
