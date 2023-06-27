package com.example.app.common.domain.dto;

import com.example.app.common.constant.ErrorCode;
import org.springframework.http.ProblemDetail;

public class ResponseDto extends ProblemDetail {
    public static ProblemDetail of(ErrorCode errorCode) {
        var problemDetail = ProblemDetail.forStatus(errorCode.getHttpStatus());
        problemDetail.setDetail(errorCode.getPhrase());
        return problemDetail;
    }
}