package com.tantan.SaleCourse.mapper;

import com.tantan.SaleCourse.entity.Teacher;
import com.tantan.SaleCourse.response.teacher.TeacherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);
    @Mapping(source ="id",target = "id")
    @Mapping(source ="email",target = "email")
    @Mapping(source ="fullName",target = "fullName")
    TeacherResponse toResponse(Teacher teacher);
}
