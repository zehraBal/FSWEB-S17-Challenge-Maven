package com.workintech.spring17challenge.entity;

public class TotalGpaCalculator {
    private static LowCourseGpa lowCourseGpa;
    private static MediumCourseGpa mediumCourseGpa;
    private static HighCourseGpa highCourseGpa;


    public static int  calculateTotalGPA(Course course){
        int totalGpa=0;
        if(course.getCredit()<=2){
           totalGpa=course.getGrade().getCoefficient() * course.getCredit() * lowCourseGpa.getGpa();
           return totalGpa;
        }else if(course.getCredit()==3){
            totalGpa=course.getGrade().getCoefficient() * course.getCredit() * mediumCourseGpa.getGpa();
            return totalGpa;
        } else if (course.getCredit()==4) {
            totalGpa=course.getGrade().getCoefficient() * course.getCredit() * highCourseGpa.getGpa();
        }
        return 0;
    }
}
