package com.tantan.SaleCourse.controller.progress;

import com.tantan.SaleCourse.request.lesson.ProgressRequest;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.progress.IProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/progress")
public class ProgressController {
    @Autowired
    private IProgressService iProgressService;
    @PostMapping("/update")
//    @PreAuthorize("hasAuthority('')")
    public ResponseEntity<BaseDataResponse> createNewProgress(@RequestBody ProgressRequest progressRequest )
    {
        iProgressService.createNewProgress(progressRequest);
        return  ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false,HttpStatus.OK.value(),"update progress successfully",null)
        );
    }
}
