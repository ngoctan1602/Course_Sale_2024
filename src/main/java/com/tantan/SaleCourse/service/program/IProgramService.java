package com.tantan.SaleCourse.service.program;

import com.tantan.SaleCourse.entity.Program;
import com.tantan.SaleCourse.request.program.ProgramRequest;
import com.tantan.SaleCourse.response.program.ProgramDTO;

import java.util.List;
import java.util.Optional;

public interface IProgramService {
    public boolean createNewProgram(ProgramRequest programRequest);
    public boolean updateProgram();
    public Program getProgramById(long id);
    public List<ProgramDTO> getAllProgram();
}
