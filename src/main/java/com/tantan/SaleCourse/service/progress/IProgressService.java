package com.tantan.SaleCourse.service.progress;

import com.tantan.SaleCourse.entity.Lesson;
import com.tantan.SaleCourse.entity.User;
import com.tantan.SaleCourse.entity.lesson.Progress;
import com.tantan.SaleCourse.request.lesson.ProgressRequest;

public interface IProgressService {
    public void createNewProgress(ProgressRequest progressRequest);
    public Progress findByUserAndProgress(User user, Lesson lesson);
}
