package com.tantan.SaleCourse.util;

import com.tantan.SaleCourse.entity.Teacher;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
public class GetTeacher {
    public Teacher getTeacher() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Teacher) authentication.getPrincipal();
    }
}