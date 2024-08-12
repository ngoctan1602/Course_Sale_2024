package com.tantan.SaleCourse.controller.enroll;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.enroll.IEnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/enroll")
public class EnrollController {
    @Autowired
    private IEnrollService iEnrollService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<BaseDataResponse> createNewCourse(@RequestParam long idCourse)
    {
       iEnrollService.createNewEnroll(idCourse);
        return  ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false,HttpStatus.OK.value(),"user enroll course successfully",null)
        );
    }
}
