package com.tantan.SaleCourse.response.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse {
    protected long id;
    protected String name;
    protected int totalTime;
    protected String description;
//    private String urlVideo;
//    private String urlFile;
}
