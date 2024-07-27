package com.tantan.SaleCourse.exception;

import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomException extends RuntimeException{
    private BaseDataResponse dataResponse;

    public CustomException(BaseDataResponse dataResponse) {
        super(dataResponse.getMessage());
        this.dataResponse = dataResponse;
    }
}