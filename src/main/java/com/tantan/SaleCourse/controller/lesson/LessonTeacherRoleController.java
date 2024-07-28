package com.tantan.SaleCourse.controller.lesson;

import com.tantan.SaleCourse.entity.Teacher;
import com.tantan.SaleCourse.util.GetTeacher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teacher/lesson")
public class LessonTeacherRoleController {
//    @GetMapping
//    @PreAuthorize("hasAuthority('TEACHER')")
//    public String getMapping()
//    {
//        return "Hello teacher";
//    }
//    @GetMapping("/getTeacher")
//    @PreAuthorize("hasAuthority('TEACHER')")
//    public Teacher getTeacher()
//    {
//        return new GetTeacher().getTeacher();
//    }
}
