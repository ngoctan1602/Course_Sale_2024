package com.tantan.SaleCourse.request.lesson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProgressRequest {
    private long idLesson;
    private int totalMinute;
}
