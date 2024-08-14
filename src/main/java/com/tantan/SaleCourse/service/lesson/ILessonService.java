package com.tantan.SaleCourse.service.lesson;

import com.tantan.SaleCourse.entity.Lesson;
import com.tantan.SaleCourse.request.lesson.LessonRequest;

import java.util.List;

public interface ILessonService {
    public boolean createNewLesson(LessonRequest lessonRequest);
    public Lesson findById(long idLesson);
    public List<Lesson> findLessonByTopicInCourse(long idTopic,long idCourse);
    public Lesson findLessonDetailById(long idLesson);
}
