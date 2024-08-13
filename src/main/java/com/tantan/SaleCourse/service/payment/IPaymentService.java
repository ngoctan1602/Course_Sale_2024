package com.tantan.SaleCourse.service.payment;

import com.tantan.SaleCourse.response.payment.PaymentResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface IPaymentService {
    public  String createPaymentVNPay(long amount,long idCourse) throws UnsupportedEncodingException;
    public boolean paymentReturn(HttpServletRequest request);
}
