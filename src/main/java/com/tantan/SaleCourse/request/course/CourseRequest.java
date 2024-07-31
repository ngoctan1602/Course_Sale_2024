package com.tantan.SaleCourse.request.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    private int price;
    private int duration;
    private String description;
    private String name;
    private long idSubject;
    private long idProgram;
    private long idTeacher;
    private Set<Long> listIdTopic;
}
