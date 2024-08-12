package com.tantan.SaleCourse.entity.course;

import com.tantan.SaleCourse.entity.Status;
import com.tantan.SaleCourse.entity.User;
import com.tantan.SaleCourse.entity.payment.Payment;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_enroll")
public class Enroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
    @Enumerated(EnumType.STRING)
    private Payment payment;
    @Enumerated(EnumType.STRING)
    private Status checkout;
    private Date dateEnroll;
    private Date expire;
}
