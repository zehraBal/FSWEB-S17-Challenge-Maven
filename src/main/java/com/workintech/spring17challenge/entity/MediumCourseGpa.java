package com.workintech.spring17challenge.entity;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class MediumCourseGpa implements CourseGpa{
    @Override
    public  int getGpa(){
        return 5;
    }
}
