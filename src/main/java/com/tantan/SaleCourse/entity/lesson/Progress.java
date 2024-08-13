package com.tantan.SaleCourse.entity.lesson;

import com.tantan.SaleCourse.entity.Lesson;
import com.tantan.SaleCourse.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "tbl_progress")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name ="lesson_id",referencedColumnName = "id")
    private Lesson lesson;
    private float progress;
}
