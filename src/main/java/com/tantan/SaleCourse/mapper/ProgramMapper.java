package com.tantan.SaleCourse.mapper;

import com.tantan.SaleCourse.entity.Program;
import com.tantan.SaleCourse.request.program.ProgramRequest;
import com.tantan.SaleCourse.response.program.ProgramDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProgramMapper {
    ProgramMapper INSTANCE = Mappers.getMapper(ProgramMapper.class);
    ProgramDTO toDTO (Program program);
    Program toEntity(ProgramRequest programRequest);
}
