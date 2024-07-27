package com.tantan.SaleCourse.service.subject;

import com.tantan.SaleCourse.entity.Subject;
import com.tantan.SaleCourse.request.subject.SubjectRequest;
import com.tantan.SaleCourse.response.subject.SubjectResponse;

import java.util.List;

public interface ISubjectService {
    public boolean createNewSubject(SubjectRequest subjectRequest);
    public Subject getSubjectById(long id);
    public List<SubjectResponse> getAllSubject();
}
