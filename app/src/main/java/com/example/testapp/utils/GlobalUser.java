package com.example.testapp.utils;

import com.example.testapp.entity.Student;

public class GlobalUser {
    private static Student student=null;
    public static void setGlobalStudent(Student s){
        student=s;
    }
    public static Student getGlobalStudent(){
        return student;
    }
}
