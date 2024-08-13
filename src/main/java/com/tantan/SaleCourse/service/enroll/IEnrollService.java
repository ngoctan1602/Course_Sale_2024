package com.tantan.SaleCourse.service.enroll;

import com.tantan.SaleCourse.entity.payment.Payment;
import org.springframework.scheduling.annotation.Async;

public interface IEnrollService {
    public boolean createNewEnroll(long id, Payment payment);
}
