package com.tantan.SaleCourse.controller.lesson;

import com.tantan.SaleCourse.request.lesson.LessonRequest;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.lesson.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/lesson")
public class LessonUserRoleController {

    @PostMapping("/create")
//    @PreAuthorize("hasAuthority('')")
    public ResponseEntity<BaseDataResponse> createNewProgress(@RequestParam long idLesson )
    {
//        iLessonService.createNewLesson(lessonRequest);
        return  ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false,HttpStatus.OK.value(),"Create new lesson successfully",null)
        );
    }

}
