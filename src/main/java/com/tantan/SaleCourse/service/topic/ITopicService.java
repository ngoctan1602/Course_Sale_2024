package com.tantan.SaleCourse.service.topic;

import com.tantan.SaleCourse.entity.Subject;
import com.tantan.SaleCourse.entity.Topic;
import com.tantan.SaleCourse.request.topic.TopicRequest;

public interface ITopicService {
    public boolean createNewTopic(TopicRequest topicRequest);
    public Topic getTopicById(long id);
}
