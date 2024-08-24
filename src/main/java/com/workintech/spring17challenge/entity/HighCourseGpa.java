package com.workintech.spring17challenge.entity;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class HighCourseGpa implements CourseGpa{
    @Override
    public int getGpa(){
        return 10;
    }
}
