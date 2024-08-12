package com.tantan.SaleCourse.util;

import com.tantan.SaleCourse.entity.Teacher;
import com.tantan.SaleCourse.entity.User;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
public class GetUser {
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}