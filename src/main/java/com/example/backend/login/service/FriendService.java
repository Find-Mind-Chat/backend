package com.example.backend.login.service;

import com.example.backend.login.dto.req.FriendRequestReqDto;
import com.example.backend.login.vo.req.FriendRequestReqVo;

import java.util.List;

public interface FriendService {
    List<String> friendList(String uuid, String type);

    Void friendRequest(String uuid, FriendRequestReqDto friendRequestReqDto);
}
