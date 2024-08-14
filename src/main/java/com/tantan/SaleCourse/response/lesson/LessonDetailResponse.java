package com.tantan.SaleCourse.response.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDetailResponse extends LessonResponse{
    private String urlVideo;
    private String urlFile;
    private float progress;
}
