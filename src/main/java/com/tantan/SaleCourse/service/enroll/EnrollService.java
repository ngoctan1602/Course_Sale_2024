package com.tantan.SaleCourse.service.enroll;

import com.tantan.SaleCourse.entity.Status;
import com.tantan.SaleCourse.entity.User;
import com.tantan.SaleCourse.entity.course.Course;
import com.tantan.SaleCourse.entity.course.Enroll;
import com.tantan.SaleCourse.entity.payment.Payment;
import com.tantan.SaleCourse.exception.CustomException;
import com.tantan.SaleCourse.repository.EnrollRepository;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.course.ICourseService;
import com.tantan.SaleCourse.service.mail.IMailService;
import com.tantan.SaleCourse.util.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;


@Service
public class EnrollService implements IEnrollService {
    @Autowired
    private ICourseService iCourseService;
    @Autowired
    private EnrollRepository enrollRepository;
    @Autowired
    private IMailService iMailService;

    @Override
    @Transactional
    public boolean createNewEnroll(long id,Payment payment) {
        Course course = iCourseService.findCourseById(id);
        User user = new GetUser().getUser();
        boolean b = this.checkCourseInUser(course, user);
        if(b){
            throw new CustomException(new BaseDataResponse(
                    true, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Course has not expired. You dont need enroll course", null
            ));
        }
        Enroll enroll = Enroll.builder()
                .dateEnroll(new Date(System.currentTimeMillis()))
                .payment(payment)
                .checkout(Status.CREATED)
                .user(user)
                .course(course)
                .expire(new Date(System.currentTimeMillis() + course.getDuration()* 24L * 60L * 60L * 1000L))
                .build();
        try {
            enrollRepository.save(enroll);
        } catch (Exception e) {
            throw new CustomException(new BaseDataResponse(
                    true, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null
            ));
        }
        try {
            iMailService.sendMail(user.getEmail(), "Đăng kí khóa học thành công", "Xin chào " + user.getFullName() + " chúc mừng bạn đã đăng kí " + course.getName() + " thành công");
        } catch (Exception e) {
            throw new CustomException(new BaseDataResponse(
                    true, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null
            ));
        }
        return false;
    }

    public boolean checkCourseInUser(Course course, User user) {
        // return true if enroll expire after now => user can not enroll course
        List<Enroll> enrolls = enrollRepository.findByUserAndCourse(user, course);
        for (Enroll enroll : enrolls) {
            if (enroll.getExpire().after(new Date(System.currentTimeMillis()))) {
                return true;
            }
        }
        return false;
    }
}
