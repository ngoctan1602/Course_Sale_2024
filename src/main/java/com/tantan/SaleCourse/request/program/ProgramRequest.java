package com.tantan.SaleCourse.request.program;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProgramRequest {
    @NotNull(message = "Name must not null")
    private String name;
    private String description;
}
