package com.workintech.spring17challenge.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public class LowCourseGpa implements CourseGpa{
    @Override
    public int getGpa(){
        return 3;
    }
}
