package com.workintech.spring17challenge.entity;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class LowCourseGpa implements CourseGpa{
    @Override
    public  int getGpa(){
        return 3;
    }
}
