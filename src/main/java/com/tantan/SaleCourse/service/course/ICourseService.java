package com.tantan.SaleCourse.service.course;

import com.tantan.SaleCourse.entity.course.Course;
import com.tantan.SaleCourse.request.course.CourseRequest;

public interface ICourseService {
    public boolean createNewCourse(CourseRequest courseRequest);
    public Course findCourseById(long idCourse);
}
