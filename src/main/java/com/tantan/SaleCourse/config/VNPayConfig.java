package com.tantan.SaleCourse.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "vnpay")
public class VNPayConfig {
    private String vnp_TmnCode;
    private String vnp_HashSecret;
    private String vnp_Url;
    private String vnp_ReturnUrl;
}
