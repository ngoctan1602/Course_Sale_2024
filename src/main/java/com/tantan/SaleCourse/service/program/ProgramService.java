package com.tantan.SaleCourse.service.program;

import com.tantan.SaleCourse.entity.Program;
import com.tantan.SaleCourse.exception.CustomException;
import com.tantan.SaleCourse.mapper.ProgramMapper;
import com.tantan.SaleCourse.repository.ProgramRepository;
import com.tantan.SaleCourse.request.program.ProgramRequest;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.response.program.ProgramDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProgramService implements IProgramService {
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private Validator validator;

    @Override
    public boolean createNewProgram(ProgramRequest programRequest) {
        Set<ConstraintViolation<ProgramRequest>> violations = validator.validate(programRequest);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Program program = Program.builder()
                .name(programRequest.getName())
                .description(programRequest.getDescription())
                .build();
        try {
            programRepository.save(program);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error when create new program");
        }
    }

    @Override
    public boolean updateProgram() {
        return false;
    }

    @Override
    public ProgramDTO getProgramById(long id) {
        Program program = programRepository.findById(id).orElse(null);
        if (null == program) {
            throw new CustomException(new BaseDataResponse(
                    false, HttpStatus.NOT_FOUND.value(), "Not found program with id" + id, null
            ));
        }
        return ProgramMapper.INSTANCE.toDTO(program);

    }

    @Override
    public List<ProgramDTO> getAllProgram() {
        List<Program> program = programRepository.findAll();
        if (program.isEmpty()) {
            throw new CustomException(new BaseDataResponse(
                    false, HttpStatus.NOT_FOUND.value(), "Not found program", null
            ));
        }
        return program.stream().map(ProgramMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
}
