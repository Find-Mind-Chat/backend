package com.example.backend.login.service;

import com.example.backend.login.dto.req.FriendUuidReqDto;
import com.example.backend.login.dto.res.FriendInfoResDto;

import java.util.List;

public interface FriendService {
    List<FriendInfoResDto> friendList(String uuid, String type);

    Void friendRequest(String uuid, FriendUuidReqDto friendUuidReqDto);

    Void friendDelete(String uuid, FriendUuidReqDto friendUuidReqDto);
}
