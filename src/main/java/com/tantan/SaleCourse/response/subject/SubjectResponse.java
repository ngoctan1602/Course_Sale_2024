package com.tantan.SaleCourse.response.subject;

import com.tantan.SaleCourse.response.teacher.TeacherResponse;
import lombok.Data;

@Data
public class SubjectResponse {
    private long id;
    private String name;
    private String description;
    private TeacherResponse teacher;
}
