package com.example.backend.login.controller;

import com.example.backend.login.jwt.dto.CustomUserDetails;
import com.example.backend.login.service.FriendService;
import com.example.backend.global.ResponseEntity;
import com.example.backend.global.ResponseSuccess;
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

    @GetMapping("/list")
    public ResponseEntity<List<String>> friendList(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(value = "type", required = false, defaultValue = "all") String type) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication: " + authentication);
        String uuid = customUserDetails.getUsername();
        return new ResponseEntity<>(ResponseSuccess.SUCCESS, friendService.friendList(uuid,type));
    }

}
