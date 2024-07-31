package com.tantan.SaleCourse.mapper;

import com.tantan.SaleCourse.entity.Subject;
import com.tantan.SaleCourse.request.subject.SubjectRequest;
import com.tantan.SaleCourse.response.subject.SubjectResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {TeacherMapper.class})
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);
    Subject toEntity(SubjectRequest subjectRequest);
    @Mapping(source = "teachers", target = "teacher")
    SubjectResponse toResponse(Subject subject);
}
