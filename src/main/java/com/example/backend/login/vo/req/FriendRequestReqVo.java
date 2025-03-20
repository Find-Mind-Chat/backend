package com.example.backend.login.vo.req;

import com.example.backend.login.dto.req.FriendRequestReqDto;
import lombok.Getter;

@Getter
public class FriendRequestReqVo {

    private String friendUuid;

    public static FriendRequestReqDto voToDto(FriendRequestReqVo friendRequestReqVo) {
        return FriendRequestReqDto.builder()
                .friendUuid(friendRequestReqVo.friendUuid)
                .build();
    }
}
