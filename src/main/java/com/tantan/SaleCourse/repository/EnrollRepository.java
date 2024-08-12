package com.tantan.SaleCourse.repository;

import com.tantan.SaleCourse.entity.User;
import com.tantan.SaleCourse.entity.course.Course;
import com.tantan.SaleCourse.entity.course.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollRepository extends JpaRepository<Enroll,Long> {

    List<Enroll> findByUserAndCourse(User user, Course course);
}
