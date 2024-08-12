package com.tantan.SaleCourse.entity;

import com.tantan.SaleCourse.entity.course.Course;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "tbl_program")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "program")
    private Set<Course> course;
}
