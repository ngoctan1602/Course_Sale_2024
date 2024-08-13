package com.tantan.SaleCourse.repository;

import com.tantan.SaleCourse.entity.Lesson;
import com.tantan.SaleCourse.entity.User;
import com.tantan.SaleCourse.entity.lesson.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress,Long> {
    Optional<Progress> findByUserAndLesson(User user, Lesson lesson);
}
