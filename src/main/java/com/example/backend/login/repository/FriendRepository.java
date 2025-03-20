package com.example.backend.login.repository;

import com.example.backend.login.domain.Friend;
import com.example.backend.login.domain.Member;
import com.example.backend.login.enums.FriendType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByMember(Member member);

    List<Friend> findByMemberAndStatus(Member member, FriendType friendType);
}
