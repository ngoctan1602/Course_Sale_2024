package com.tantan.SaleCourse.service.topic;

import com.tantan.SaleCourse.entity.Subject;
import com.tantan.SaleCourse.entity.Topic;
import com.tantan.SaleCourse.exception.CustomException;
import com.tantan.SaleCourse.mapper.TopicMapper;
import com.tantan.SaleCourse.repository.TopicRepository;
import com.tantan.SaleCourse.request.topic.TopicRequest;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.subject.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicService implements ITopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ISubjectService iSubjectService;

    @Override
    @Transactional
    public boolean createNewTopic(TopicRequest topicRequest) {
        var subject = iSubjectService.getSubjectById(topicRequest.getIdSubject());
        Topic topic = TopicMapper.INSTANCE.toEntity(topicRequest);
        topic.setSubject(subject);
        try {
            topicRepository.save(topic);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Topic getTopicById(long id) {
        Topic topic = topicRepository.findById(id).orElse(null);
        if (topic == null) {
            throw new CustomException(
                    new BaseDataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found topic with id = " + id, null)
            );
        }
        return topic;
    }
}
