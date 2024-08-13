package com.tantan.SaleCourse.service.payment;

import com.tantan.SaleCourse.config.VNPayConfig;
import com.tantan.SaleCourse.entity.payment.Payment;
import com.tantan.SaleCourse.exception.CustomException;
import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import com.tantan.SaleCourse.response.payment.PaymentResponse;
import com.tantan.SaleCourse.service.enroll.IEnrollService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private VNPayConfig vnpayConfig;
    @Autowired
    private IEnrollService iEnrollService;

    @Override
    public String createPaymentVNPay(long amount, long idCourse) throws UnsupportedEncodingException {
        String vnp_TxnRef = String.valueOf(System.currentTimeMillis());
        String vnp_TmnCode = vnpayConfig.getVnp_TmnCode();
        String vnp_HashSecret = vnpayConfig.getVnp_HashSecret();
        String vnp_Url = vnpayConfig.getVnp_Url();
        String vnp_ReturnUrl = vnpayConfig.getVnp_ReturnUrl();

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount * 100));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang: " + vnp_TxnRef + "Ma khoa hoc: " + idCourse);
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", "127.0.0.1");
        vnp_Params.put("vnp_CreateDate", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
                hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII)).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                if (!fieldName.equals(fieldNames.get(fieldNames.size() - 1))) {
                    hashData.append('&');
                    query.append('&');
                }
            }
        }
        String vnp_SecureHash = hmacSHA512(vnp_HashSecret, hashData.toString());
        query.append("&vnp_SecureHash=").append(vnp_SecureHash);
        return vnp_Url + "?" + query.toString();
    }

    @Override
    public boolean paymentReturn(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        Map<String, String> vnp_Params = new HashMap<>();
        for (String key : params.keySet()) {
            vnp_Params.put(key, params.get(key)[0]);
        }
        String vnp_SecureHash = vnp_Params.remove("vnp_SecureHash");


        String vnp_HashSecret = vnpayConfig.getVnp_HashSecret();
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
                hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                if (!fieldName.equals(fieldNames.get(fieldNames.size() - 1))) {
                    hashData.append('&');
                }
            }
        }
        String calculatedHash = hmacSHA512(vnp_HashSecret, hashData.toString());
        if (calculatedHash.equals(vnp_SecureHash)) {
            // Kiểm tra kết quả thanh toán thành công
            if ("00".equals(vnp_Params.get("vnp_ResponseCode"))) {

                String text = vnp_Params.get("vnp_OrderInfo");
                String[] parts = text.split("Ma khoa hoc: ");
                String courseCode = parts[1].trim();
                iEnrollService.createNewEnroll(Long.parseLong(courseCode), Payment.VNPAY);
                return true;
            } else {
                throw new CustomException(
                        new BaseDataResponse(true, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Checkout failed", null)
                );
            }
        } else {
            throw new CustomException(
                    new BaseDataResponse(true, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Checkout failed", null)
            );
        }
    }

    private static String hmacSHA512(String key, String data) {
        try {
            Mac hmac512 = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA512");
            hmac512.init(secretKey);
            byte[] hash = hmac512.doFinal(data.getBytes());
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Không thể tạo chữ ký HmacSHA512", e);
        }
    }
}
