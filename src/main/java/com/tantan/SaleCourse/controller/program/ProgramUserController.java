package com.tantan.SaleCourse.controller.program;

import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.program.IProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/program")
public class ProgramUserController {
    @Autowired
    private IProgramService iProgramService;
    @GetMapping("/getById")
    public ResponseEntity<BaseDataResponse> getById(@RequestParam long id)
    {
        var program =  iProgramService.getProgramById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false,HttpStatus.OK.value(),"Get program with id = "+ id + " successfully",program)
        );
    }
    @GetMapping("/getAll")
    public ResponseEntity<BaseDataResponse> getAll()
    {
        var program =  iProgramService.getAllProgram();
        return  ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false,HttpStatus.OK.value(),"Get program successfully",program)
        );
    }
}
