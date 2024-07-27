package com.tantan.SaleCourse.controller.topic;

import com.tantan.SaleCourse.request.subject.SubjectRequest;
import com.tantan.SaleCourse.request.topic.TopicRequest;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.topic.ITopicService;
import com.tantan.SaleCourse.service.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/topic")
public class TopicAdminController {
    @Autowired
    private ITopicService iTopicService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<BaseDataResponse> createNewProgram(@RequestBody TopicRequest topicRequest)
    {
        boolean b  = iTopicService.createNewTopic(topicRequest);
        return  ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(b,HttpStatus.OK.value(),"Create new topic successfully",null)
        );
    }
}
