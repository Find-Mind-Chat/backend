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

    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member friend;

    private Boolean star;

    private FriendType status;

    @Builder
    public Friend(String uuid, Member friend, Boolean star, FriendType status) {
        this.uuid = uuid;
        this.friend = friend;
        this.star = star;
        this.status = status;
    }
}
