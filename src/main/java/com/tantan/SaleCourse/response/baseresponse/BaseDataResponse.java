package com.tantan.SaleCourse.response.baseresponse;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BaseDataResponse {
    private boolean isError;
    private int httpStatusCode;
    private String message;
    private Object data;
}
