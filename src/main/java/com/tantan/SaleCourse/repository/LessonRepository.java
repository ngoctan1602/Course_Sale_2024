package com.tantan.SaleCourse.repository;

import com.tantan.SaleCourse.entity.Lesson;
import com.tantan.SaleCourse.entity.Topic;
import com.tantan.SaleCourse.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    List<Lesson> findByTopicAndCourse(Topic topic, Course course);
}
