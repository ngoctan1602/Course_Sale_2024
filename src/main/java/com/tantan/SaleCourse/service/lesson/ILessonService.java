package com.tantan.SaleCourse.service.lesson;

import com.tantan.SaleCourse.request.lesson.LessonRequest;

public interface ILessonService {
    public boolean createNewLesson(LessonRequest lessonRequest);
}
