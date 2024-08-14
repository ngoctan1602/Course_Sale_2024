package com.tantan.SaleCourse.controller.lesson;

import com.tantan.SaleCourse.entity.Lesson;
import com.tantan.SaleCourse.entity.User;
import com.tantan.SaleCourse.entity.lesson.Progress;
import com.tantan.SaleCourse.mapper.LessonMapper;
import com.tantan.SaleCourse.request.lesson.LessonRequest;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.response.lesson.LessonDetailResponse;
import com.tantan.SaleCourse.response.lesson.LessonResponse;
import com.tantan.SaleCourse.service.lesson.ILessonService;
import com.tantan.SaleCourse.service.progress.IProgressService;
import com.tantan.SaleCourse.util.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user/lesson")
public class LessonUserRoleController {
    @Autowired
    private ILessonService iLessonService;
    @Autowired
    private IProgressService iProgressService;
    @GetMapping("/getByTopicInCourse")
    public ResponseEntity<BaseDataResponse> getAllLessonInTopicOfCourse(@RequestParam long idTopic, @RequestParam long idCourse) {
        List<LessonResponse> lessonResponses = iLessonService.findLessonByTopicInCourse(idTopic, idCourse).stream()
                .map(LessonMapper.INSTANCE::toResponse).toList();
        return ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false, HttpStatus.OK.value(), "get lesson by topic in course successfully", lessonResponses)
        );
    }

    @GetMapping("/getById")
    public ResponseEntity<BaseDataResponse> getLessonById(@RequestParam long idLesson) {
        Lesson lesson = iLessonService.findLessonDetailById(idLesson);
        Progress progress = iProgressService.findByUserAndProgress(new GetUser().getUser(),lesson);
        LessonDetailResponse lessonResponses = LessonMapper.INSTANCE.toDetail(lesson);
        if (progress!=null)
        {
            lessonResponses.setProgress(progress.getProgress());
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false, HttpStatus.OK.value(), "get lesson by id successfully", lessonResponses)
        );
    }

}
