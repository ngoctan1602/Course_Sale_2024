package com.tantan.SaleCourse.service.course;

import com.tantan.SaleCourse.entity.*;
import com.tantan.SaleCourse.entity.course.Course;
import com.tantan.SaleCourse.exception.CustomException;
import com.tantan.SaleCourse.mapper.CourseMapper;
import com.tantan.SaleCourse.repository.CourseRepository;
import com.tantan.SaleCourse.request.course.CourseRequest;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.program.IProgramService;
import com.tantan.SaleCourse.service.subject.ISubjectService;
import com.tantan.SaleCourse.service.teacher.ITeacherService;
import com.tantan.SaleCourse.service.topic.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ITeacherService iTeacherService;
    @Autowired
    private ISubjectService iSubjectService;
    @Autowired
    private IProgramService iProgramService;
    @Autowired
    private ITopicService iTopicService;

    @Override
    @Transactional
    public boolean createNewCourse(CourseRequest courseRequest) {
        // Check program, teacher and subject has already
        Program program = iProgramService.getProgramById(courseRequest.getIdProgram());
        Teacher teacher = iTeacherService.findTeacherById(courseRequest.getIdTeacher());
        Subject subject = iSubjectService.getSubjectById(courseRequest.getIdSubject());
        Course course = CourseMapper.INSTANCE.toEntity(courseRequest);
        // Set prop for course
        course.setTopics(this.setTopicForCourse(subject,courseRequest.getListIdTopic()));
        course.setSubject(subject);
        course.setTeacher(teacher);
        course.setProgram(program);
        try {
            courseRepository.save(course);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Course findCourseById(long idCourse) {
        Course course = courseRepository.findById(idCourse).orElse(null);
        if(course == null)
        {
            throw  new CustomException(
                    new BaseDataResponse(true,HttpStatus.NOT_FOUND.value(), "Not found course with id = "+idCourse,null)
            );
        }
        return course;
    }

    private Set<Topic> setTopicForCourse(Subject subject, Set<Long> idTopics) {
        Set<Topic> topics = new HashSet<>();
        for (Long idTopic : idTopics) {
            Topic topic = iTopicService.getTopicById(idTopic);
            boolean b = subject.getTopics().contains(topic);
            if (!b) {
                throw new CustomException(
                        new BaseDataResponse(true, HttpStatus.NOT_FOUND.value(), "Topic has not found in subject", null)
                );
            }
            topics.add(topic);
        }
        return topics;
    }
}
