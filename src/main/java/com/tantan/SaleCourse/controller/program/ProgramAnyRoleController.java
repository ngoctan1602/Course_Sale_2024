package com.tantan.SaleCourse.controller.program;

import com.tantan.SaleCourse.controller.testasync.IAnsyncService;
import com.tantan.SaleCourse.mapper.ProgramMapper;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.program.IProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/program")
public class ProgramAnyRoleController {
    @Autowired
    private IProgramService iProgramService;
    @Autowired
    private IAnsyncService iAnsyncService;

    @GetMapping("/getById")
    public ResponseEntity<BaseDataResponse> getById(@RequestParam long id) {
        var program = iProgramService.getProgramById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false, HttpStatus.OK.value(), "Get program with id = " + id + " successfully", ProgramMapper.INSTANCE.toDTO(program))
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<BaseDataResponse> getAll() {
        var program = iProgramService.getAllProgram();
        return ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false, HttpStatus.OK.value(), "Get program successfully", program)
        );
    }

    @PostMapping("/testasync")
    public String addLesson(@RequestParam String video, @RequestParam String image) {
        CompletableFuture<Void> videoFuture = iAnsyncService.saveVideo(video);
        CompletableFuture<Void> imageFuture = iAnsyncService.saveImage(image);

//        return CompletableFuture.allOf(videoFuture, imageFuture)
//                .thenApply(v -> "Lesson added successfully with video and image");
        return "Lesson added successfully with video and image";
    }
}
