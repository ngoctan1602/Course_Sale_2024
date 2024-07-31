package com.tantan.SaleCourse.service.teacher;

import com.tantan.SaleCourse.entity.Teacher;
import com.tantan.SaleCourse.exception.CustomException;
import com.tantan.SaleCourse.repository.TeacherRepository;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TeacherService implements ITeacherService{
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public Teacher findTeacherById(long id) {

          Teacher teacher=teacherRepository.findById(id).orElse(null);
          if (teacher!=null)
          {
           return  teacher;
          }
          throw  new CustomException(new BaseDataResponse(
                  true, HttpStatus.NOT_FOUND.value(), "Not found teacher with id = "+ id,
                  null
          ));
    }
}
