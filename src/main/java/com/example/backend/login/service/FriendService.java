package com.example.backend.login.service;

import java.util.List;

public interface FriendService {
    List<String> friendList(String uuid, String type);
}
