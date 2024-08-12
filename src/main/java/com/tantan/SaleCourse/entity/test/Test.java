package com.tantan.SaleCourse.entity.test;

import com.tantan.SaleCourse.entity.course.Course;
import com.tantan.SaleCourse.entity.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "tbl_test")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private int totalQuestion;
    private int totalTime;
    @ManyToMany(mappedBy = "tests")
    private List<Topic> topics;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToMany(mappedBy = "tests")
    private List<Question> questions;
}
