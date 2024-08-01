package com.tantan.SaleCourse.mapper;

import com.tantan.SaleCourse.entity.Lesson;
import com.tantan.SaleCourse.request.lesson.LessonRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LessonMapper {
    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);
    @Mapping(source = "name",target = "name")
    @Mapping(source = "totalTime",target = "totalTime")
    @Mapping(source = "description",target = "description")
    Lesson toEntity(LessonRequest lessonRequest);
}
