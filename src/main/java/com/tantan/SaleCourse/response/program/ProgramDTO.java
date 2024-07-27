package com.tantan.SaleCourse.response.program;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDTO {
    private long id;
    private String name;
    private String description;
}
