package com.tantan.SaleCourse.service.lesson;

import com.tantan.SaleCourse.entity.User;
import com.tantan.SaleCourse.entity.course.Course;
import com.tantan.SaleCourse.entity.Lesson;
import com.tantan.SaleCourse.entity.Teacher;
import com.tantan.SaleCourse.entity.Topic;
import com.tantan.SaleCourse.exception.CustomException;
import com.tantan.SaleCourse.mapper.LessonMapper;
import com.tantan.SaleCourse.repository.LessonRepository;
import com.tantan.SaleCourse.request.lesson.LessonRequest;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.cloudinary.ICloudServices;
import com.tantan.SaleCourse.service.course.ICourseService;
import com.tantan.SaleCourse.service.enroll.EnrollService;
import com.tantan.SaleCourse.service.enroll.IEnrollService;
import com.tantan.SaleCourse.service.topic.ITopicService;
import com.tantan.SaleCourse.util.GetTeacher;
import com.tantan.SaleCourse.util.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LessonService implements ILessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ICloudServices iCloudServices;
    @Autowired
    private ITopicService iTopicService;
    @Autowired
    private ICourseService iCourseService;
    @Autowired
    private EnrollService enrollService;

    private boolean topicExistInCourse(Topic topic, Course course) {
        boolean isExist = false;
        for (Topic topic1 : course.getTopics()) {
            if (topic1.equals(topic)) {
                isExist = true;
            }
        }
        if (!isExist) {
            throw new CustomException(
                    new BaseDataResponse(true, HttpStatus.NOT_ACCEPTABLE.value(), "Course does not contain topic", null)
            );
        }
        return true;
    }

    @Override
    @Transactional
    public boolean createNewLesson(LessonRequest lessonRequest) {
        // Check contraint course with teacher and topic with course
        Course course = iCourseService.findCourseById(lessonRequest.getIdCourse());
        Topic topic = iTopicService.getTopicById(lessonRequest.getIdTopic());
        Teacher teacher = new GetTeacher().getTeacher();
        if (!(course.getTeacher().getId() == teacher.getId())) {
            throw new CustomException(
                    new BaseDataResponse(true, HttpStatus.NOT_ACCEPTABLE.value(), "Teacher does not accepted create lesson because teacher does not teach subject", null)
            );
        }
        boolean isExist = this.topicExistInCourse(topic, course);
        String urlVideo = null;
        if (lessonRequest.getVideo() != null) {
            urlVideo = iCloudServices.uploadVideo(lessonRequest.getVideo(), "video", "video");
//            urlVideo = iCloudServices.uploadVideo(lessonRequest.getVideo(),"video","video");
        }
        String urlFile = null;
        if (lessonRequest.getFile() != null) {
            urlFile = iCloudServices.uploadFile(lessonRequest.getFile());
        }
        Lesson lesson = LessonMapper.INSTANCE.toEntity(lessonRequest);
        lesson.setCourse(course);
        lesson.setUrlFile(urlFile);
        lesson.setUrlVideo(urlVideo);
        lesson.setTopic(topic);
        lesson.setTeacher(teacher);
        try {
            lessonRepository.save(lesson);
            return true;
        } catch (Exception e) {
            throw new CustomException(new BaseDataResponse(true, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }

    }

    @Override
    public Lesson findById(long idLesson) {
        Lesson lesson = lessonRepository.findById(idLesson).orElse(null);
        if (lesson == null) {
            throw new CustomException(
                    new BaseDataResponse(
                            true, HttpStatus.NOT_FOUND.value(), "Not found with lesson has id = " + idLesson, null
                    )
            );
        }
        return lesson;
    }

    @Override
    public List<Lesson> findLessonByTopicInCourse(long idTopic, long idCourse) {
        Topic topic = iTopicService.getTopicById(idTopic);
        Course course = iCourseService.findCourseById(idCourse);
        this.topicExistInCourse(topic, course);
        return lessonRepository.findByTopicAndCourse(topic, course);
    }

    @Override
    public Lesson findLessonDetailById(long idLesson) {
        Lesson lesson = lessonRepository.findById(idLesson).orElseThrow(
                ()-> new CustomException( new BaseDataResponse(true,HttpStatus.NOT_FOUND.value(),
                        "Not found lesson by id =" +idLesson,null))
        );
        User user = new GetUser().getUser();
        if (!enrollService.checkCourseInUser(lesson.getCourse(), user)) {
            throw new CustomException(new BaseDataResponse(
                    true, HttpStatus.NOT_ACCEPTABLE.value(), "User has not enroll course or it has been expire", null
            ));
        }
        return lesson;
    }
}
