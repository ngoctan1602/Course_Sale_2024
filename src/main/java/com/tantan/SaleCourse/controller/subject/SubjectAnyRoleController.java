package com.tantan.SaleCourse.controller.subject;

import com.tantan.SaleCourse.mapper.SubjectMapper;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.subject.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectAnyRoleController {
    @Autowired
    private ISubjectService iSubjectService;
    @GetMapping("/getById")
    public ResponseEntity<BaseDataResponse> getById(@RequestParam long id)
    {
        var subject =  iSubjectService.getSubjectById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false,HttpStatus.OK.value(),"Get subject with id = "+ id + " successfully", SubjectMapper.INSTANCE.toResponse(subject))
        );
    }
    @GetMapping("/getAll")
    public ResponseEntity<BaseDataResponse> getAll()
    {
        var subject =  iSubjectService.getAllSubject();
        return  ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false,HttpStatus.OK.value(),"Get all subject successfully",subject)
        );
    }

}
