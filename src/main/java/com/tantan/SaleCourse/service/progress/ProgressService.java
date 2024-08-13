package com.tantan.SaleCourse.service.progress;

import com.tantan.SaleCourse.entity.Lesson;
import com.tantan.SaleCourse.entity.User;
import com.tantan.SaleCourse.entity.lesson.Progress;
import com.tantan.SaleCourse.exception.CustomException;
import com.tantan.SaleCourse.repository.LessonRepository;
import com.tantan.SaleCourse.repository.ProgressRepository;
import com.tantan.SaleCourse.request.lesson.ProgressRequest;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.lesson.ILessonService;
import com.tantan.SaleCourse.util.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProgressService implements IProgressService {
    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private ILessonService iLessonService;

    @Override
    public void createNewProgress(ProgressRequest progressRequest) {
        int totalMinute = progressRequest.getTotalMinute();
        User user = new GetUser().getUser();
        Lesson lesson = iLessonService.findById(progressRequest.getIdLesson());
        Progress progress = this.findByUserAndProgress(user, lesson);
        if(totalMinute> lesson.getTotalTime())
        {
            throw new CustomException(
                    new BaseDataResponse(true, HttpStatus.INTERNAL_SERVER_ERROR.value(), "total minute must < totaltime of lesson", null)
            );
        }
        if (progress == null) {
            progress = Progress.builder()
                    .progress((float) totalMinute / lesson.getTotalTime())
                    .user(user)
                    .lesson(lesson)
                    .build();
        } else {
            progress.setProgress((float) totalMinute / lesson.getTotalTime());
        }
        try {
            progressRepository.save(progress);
        } catch (Exception e) {
            throw new CustomException(
                    new BaseDataResponse(true, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null)
            );
        }

    }

    @Override
    public Progress findByUserAndProgress(User user, Lesson lesson) {
        return progressRepository.findByUserAndLesson(user, lesson).orElse(null);
    }
}
