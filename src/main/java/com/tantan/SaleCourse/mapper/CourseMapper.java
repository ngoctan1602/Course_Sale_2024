package com.tantan.SaleCourse.mapper;

import com.tantan.SaleCourse.entity.Course;
import com.tantan.SaleCourse.request.course.CourseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {
   CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
   @Mapping(source = "price" ,target = "price")
   @Mapping(source = "duration" ,target = "duration")
   @Mapping(source = "description" ,target = "description")
   @Mapping(source = "name" ,target = "name")
   Course toEntity(CourseRequest courseRequest);
}
