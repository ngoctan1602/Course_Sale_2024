package com.tantan.SaleCourse.controller.program;

import com.tantan.SaleCourse.request.program.ProgramRequest;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.program.IProgramService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/program")
public class ProgramController {
    @Autowired
    private IProgramService iProgramService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<BaseDataResponse> createNewProgram( @RequestBody  ProgramRequest programRequest)
    {
     iProgramService.createNewProgram(programRequest);
        return  ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false,HttpStatus.OK.value(),"Create new program",null)
        );
    }



}
