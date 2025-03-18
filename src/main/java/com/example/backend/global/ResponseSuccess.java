package com.example.backend.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    SUCCESS(200, "요청에 성공했습니다.");



    private final int code;
    private final String message;

}
