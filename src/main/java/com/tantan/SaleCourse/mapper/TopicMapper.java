package com.tantan.SaleCourse.mapper;


import com.tantan.SaleCourse.entity.Topic;
import com.tantan.SaleCourse.request.topic.TopicRequest;
import com.tantan.SaleCourse.response.topic.TopicResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TopicMapper {
    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);
    @Mapping(source = "name",target = "name")
    @Mapping(source = "description",target = "description")
    Topic toEntity(TopicRequest topicRequest);

    TopicResponse toResponse(Topic topic);
}
