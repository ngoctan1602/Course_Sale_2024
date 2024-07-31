package com.tantan.SaleCourse.repository;

import com.tantan.SaleCourse.entity.Subject;
import com.tantan.SaleCourse.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic,Long> {
}
