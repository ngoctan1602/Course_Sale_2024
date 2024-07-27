package com.tantan.SaleCourse.controller.subject;
import com.tantan.SaleCourse.request.subject.SubjectRequest;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.subject.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/subject")
public class SubjectAdminController {
    @Autowired
    private ISubjectService iSubjectService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<BaseDataResponse> createNewProgram(@RequestBody SubjectRequest subjectRequest)
    {
        boolean b=iSubjectService.createNewSubject(subjectRequest);
        return  ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(b,HttpStatus.OK.value(),"Create new subject successfully",null)
        );
    }
}
