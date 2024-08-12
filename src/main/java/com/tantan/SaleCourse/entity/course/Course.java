package com.tantan.SaleCourse.entity.course;

import com.tantan.SaleCourse.entity.*;
import com.tantan.SaleCourse.entity.test.Test;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Table(name = "tbl_course")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int price;
    private int duration; // how long duration ( day)
    private String description;
    private String name;
    @ManyToOne
    @JoinColumn(name = "program_id",referencedColumnName = "id")
    private Program program;
    @ManyToOne
    @JoinColumn(name = "subject_id",referencedColumnName = "id")
    private Subject subject;
    @ManyToMany
    @JoinTable(name = "tbl_course_topic",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<Topic> topics;
    @OneToMany(mappedBy = "course")
    private Set<Lesson> lessons;
    @OneToMany(mappedBy = "course")
    private Set<Test> tests;
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;
    @OneToMany(mappedBy = "course")
    private Set<Enroll> enrolls;
}
