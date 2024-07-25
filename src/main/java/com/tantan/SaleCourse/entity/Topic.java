package com.tantan.SaleCourse.entity;

import com.tantan.SaleCourse.entity.test.Test;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_topic")
@Entity
@Getter
@Setter
@Builder
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @ManyToMany(mappedBy = "topics")
    private Set<Subject> subjects;
    @ManyToMany(mappedBy = "topics")
    private Set<Course> courses;
    @OneToMany(mappedBy = "topic")
    private Set<Lesson> lessons;
    @ManyToMany
    @JoinTable(name = "topic_test",
    joinColumns = @JoinColumn(name = "topic_id"),
    inverseJoinColumns = @JoinColumn(name="test_id"))
    private List<Test> tests;
}
