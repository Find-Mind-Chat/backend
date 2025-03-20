package com.example.backend.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    SUCCESS(200, "요청에 성공했습니다."),
    FRIEND_LIST_SUCCESS(201, "리스트를 불러왔습니다."),
    FRIEND_ADD_SUCCESS(202, "친구를 추가했습니다."),
    FRIEND_DELETE_SUCCESS(203, "친구를 삭제했습니다.");




    private final int code;
    private final String message;

}
