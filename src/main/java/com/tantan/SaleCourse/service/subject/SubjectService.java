package com.tantan.SaleCourse.service.subject;

import com.tantan.SaleCourse.entity.Subject;
import com.tantan.SaleCourse.exception.CustomException;
import com.tantan.SaleCourse.mapper.SubjectMapper;
import com.tantan.SaleCourse.repository.SubjectRepository;
import com.tantan.SaleCourse.request.subject.SubjectRequest;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.response.subject.SubjectResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubjectService implements ISubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private Validator validator;

    @Override
    public boolean createNewSubject(SubjectRequest subjectRequest) {
        Set<ConstraintViolation<SubjectRequest>> violations = validator.validate(subjectRequest);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Subject subject = SubjectMapper.INSTANCE.toEntity(subjectRequest);
        try {
            subjectRepository.save(subject);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Subject getSubjectById(long id) {
        Subject subject = subjectRepository.findById(id).orElse(null);
        if(null == subject)
        {
            throw new CustomException(
                    new BaseDataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found subject with id = "+id,null)
            );
        }
        return subject;
    }

    @Override
    public List<SubjectResponse> getAllSubject() {
        List<Subject> subject = subjectRepository.findAll();
        if(subject.isEmpty())
        {
            throw new CustomException(
                    new BaseDataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found subject",null)
            );
        }
        return subject.stream().map(
                SubjectMapper.INSTANCE::toResponse
        ).collect(Collectors.toList());
    }
}
