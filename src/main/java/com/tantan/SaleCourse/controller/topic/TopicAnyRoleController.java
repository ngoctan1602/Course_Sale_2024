package com.tantan.SaleCourse.controller.topic;

import com.tantan.SaleCourse.mapper.TopicMapper;
import com.tantan.SaleCourse.request.topic.TopicRequest;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.topic.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/topic")
public class TopicAnyRoleController {
    @Autowired
    private ITopicService iTopicService;
    @GetMapping("/getById")
    public ResponseEntity<BaseDataResponse> createNewProgram(@RequestParam long id)
    {
      var topic = iTopicService.getTopicById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false,HttpStatus.OK.value(),"Get topic with id = "+id+" successfully", TopicMapper.INSTANCE.toResponse(topic))
        );
    }
}
