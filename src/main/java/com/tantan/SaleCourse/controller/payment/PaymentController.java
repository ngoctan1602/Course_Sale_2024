package com.tantan.SaleCourse.controller.payment;

import com.tantan.SaleCourse.config.VNPayConfig;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.service.payment.IPaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/user/payment")
public class PaymentController {
    @Autowired
    private VNPayConfig vnpayConfig;
    @Autowired
    private IPaymentService iPaymentService;

    @PostMapping("/create")
    public ResponseEntity<BaseDataResponse> createPayment(@RequestParam long amount, @RequestParam long idCourse) throws UnsupportedEncodingException {
        String urlPayment = iPaymentService.createPaymentVNPay(amount, idCourse);
        return ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false, HttpStatus.OK.value(), urlPayment, null)
        );
    }

    @GetMapping("/vnpay-return")
    public ResponseEntity<BaseDataResponse> vnpayReturn(HttpServletRequest request) throws UnsupportedEncodingException {
        iPaymentService.paymentReturn(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                new BaseDataResponse(false, HttpStatus.OK.value(),"You enroll course with vnpay successfully" , null)
        );
    }

}
