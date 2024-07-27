package com.tantan.SaleCourse.repository;

import com.tantan.SaleCourse.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {
}
