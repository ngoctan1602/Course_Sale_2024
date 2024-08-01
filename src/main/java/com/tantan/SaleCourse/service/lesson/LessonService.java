package com.tantan.SaleCourse.service.lesson;

import com.tantan.SaleCourse.entity.Course;
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
import com.tantan.SaleCourse.service.topic.ITopicService;
import com.tantan.SaleCourse.util.GetTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private boolean topicExistInCourse(Topic topic, Course course)
    {
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
        boolean isExist = this.topicExistInCourse(topic,course);
        String urlVideo = null;
        if (lessonRequest.getVideo() != null) {
            urlVideo = iCloudServices.uploadVideo(lessonRequest.getVideo(),"video","video");
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
}
