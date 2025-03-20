package com.example.backend.login.controller;

import com.example.backend.global.ResponseStatus;
import com.example.backend.login.dto.req.FriendRequestReqDto;
import com.example.backend.login.jwt.dto.CustomUserDetails;
import com.example.backend.login.service.FriendService;
import com.example.backend.global.ResponseEntity;
import com.example.backend.global.ResponseSuccess;
import com.example.backend.login.vo.req.FriendRequestReqVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/friend")
public class FriendController {

    private final FriendService friendService;

    //친구 리스트, 요청목록, 보낸목록
    @GetMapping("/list")
    public ResponseEntity<List<String>> friendList(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(value = "type", required = false, defaultValue = "all") String type) {

        String uuid = customUserDetails.getUsername();
        return new ResponseEntity<>(ResponseSuccess.FRIEND_LIST_SUCCESS, friendService.friendList(uuid,type));
    }

    //친구 신청
    @PostMapping("/request")
    public ResponseEntity<Void> friendRequest(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody FriendRequestReqVo friendRequestReqVo) {
        String uuid = customUserDetails.getUsername();

        return new ResponseEntity<>(ResponseSuccess.FRIEND_ADD_SUCCESS, friendService.friendRequest(uuid, FriendRequestReqVo.voToDto(friendRequestReqVo)));
    }
}
