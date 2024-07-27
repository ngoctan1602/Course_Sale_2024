package com.tantan.SaleCourse.response.topic;

import com.tantan.SaleCourse.response.subject.SubjectResponse;
import lombok.Data;

@Data
public class TopicResponse {
    private long id;
    private String name;
    private String description;
    private SubjectResponse subject;
}
