package com.tantan.SaleCourse.controller.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class UserController {
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAdmin() {
        return "Secured Endpoint :: GET - Admin controller";
    }
}
