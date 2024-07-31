package com.tantan.SaleCourse.controller.course;
import com.tantan.SaleCourse.request.course.CourseRequest;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/admin/course")
public class CourseAdminRoleController {
    @Autowired
    private ICourseService iCourseService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<BaseDataResponse> createNewCourse(@RequestBody CourseRequest courseRequest)
    {
        iCourseService.createNewCourse(courseRequest);
        return  ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false,HttpStatus.OK.value(),"Create new course successfully",null)
        );
    }
}
