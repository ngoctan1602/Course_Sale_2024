package com.tantan.SaleCourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_subject")
@Entity
@Getter
@Setter
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "subject")
    private Set<Course> courseSet ;
    @ManyToMany
    @JoinTable(name = "tbl_subject_topic",
    joinColumns = @JoinColumn(name = "subject_id"),
    inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<Topic> topics;
}
