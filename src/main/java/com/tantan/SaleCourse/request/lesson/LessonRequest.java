package com.tantan.SaleCourse.request.lesson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LessonRequest {
    private String name;
    private int totalTime;
    private String description;
    private MultipartFile video;
    private MultipartFile file;
    private long idTopic;
    private long idCourse;
}
