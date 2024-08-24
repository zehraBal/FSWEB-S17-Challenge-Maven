package com.workintech.spring17challenge.controller;

import com.workintech.spring17challenge.model.Course;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {
    public List<Course> courses;

    @PostConstruct
    public void init(){
        courses=new ArrayList<>();
    }

}
