package com.tantan.SaleCourse.controller.subject;

import com.tantan.SaleCourse.mapper.SubjectMapper;
import com.tantan.SaleCourse.mapper.TeacherMapper;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.response.teacher.TeacherResponse;
import com.tantan.SaleCourse.service.subject.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectAnyRoleController {
    @Autowired
    private ISubjectService iSubjectService;

    @GetMapping("/getById")
    public ResponseEntity<BaseDataResponse> getById(@RequestParam long id) {
        var subject = iSubjectService.getSubjectById(id);
        var subjectResponse = SubjectMapper.INSTANCE.toResponse(subject);
        return ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false, HttpStatus.OK.value(), "Get subject with id = " + id + " successfully", subjectResponse)
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<BaseDataResponse> getAll() {
        var subject = iSubjectService.getAllSubject();
        return ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false, HttpStatus.OK.value(), "Get all subject successfully", subject)
        );
    }

}
