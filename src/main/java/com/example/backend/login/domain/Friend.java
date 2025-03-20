package com.example.backend.login.domain;

import com.example.backend.login.enums.FriendType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String friendUuid;

    private Boolean star;

    private FriendType status;

    @Builder
    public Friend(Member member, String friendUuid, Boolean star, FriendType status) {
        this.member = member;
        this.friendUuid = friendUuid;
        this.star = star;
        this.status = status;
    }
}
