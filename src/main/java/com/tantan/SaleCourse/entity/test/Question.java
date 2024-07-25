package com.tantan.SaleCourse.entity.test;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Table(name = "tbl_question")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private String description;
    private List<String> urlImage;
    @ManyToMany
    @JoinTable(name = "test_question",
    joinColumns = @JoinColumn(name = "question_id"),
    inverseJoinColumns = @JoinColumn(name = "test_id"))
    private List<Test> tests;
    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;
}
