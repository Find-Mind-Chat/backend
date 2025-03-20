package com.example.backend.login.service;

import com.example.backend.global.GlobalException;
import com.example.backend.global.ResponseStatus;
import com.example.backend.login.domain.Friend;
import com.example.backend.login.domain.Member;
import com.example.backend.login.dto.req.FriendRequestReqDto;
import com.example.backend.login.enums.FriendType;
import com.example.backend.login.repository.FriendRepository;
import com.example.backend.login.repository.MemberRespository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FriendSerivceImp implements FriendService {

    private final FriendRepository friendRepository;
    private final MemberRespository memberRespository;


    @Override
    public List<String> friendList(String uuid, String type) {

        Member member = memberRespository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_FOUND_MEMBER));

        List<Friend> friendList = friendRepository.findByMemberAndStatus(member,
        type.equals("all") ? FriendType.FRIEND : type.equals("send") ? FriendType.SENDER : type.equals("receive") ? FriendType.RECEIVER: null);

        List<String> friendUuidList = new ArrayList<>();

        for (Friend friend : friendList){
            friendUuidList.add(friend.getFriendUuid());
        }

        return friendUuidList;
    }

    @Override
    public Void friendRequest(String uuid, FriendRequestReqDto friendRequestReqDto) {

        Friend friend = Friend.builder()
                .member(memberRespository.findByUuid(uuid).get())
                .friendUuid(friendRequestReqDto.getFriendUuid())
                .status(FriendType.SENDER)
                .build();

        friendRepository.save(friend);

        //친구 객체
        Friend friend2 = Friend.builder()
                .member(memberRespository.findByUuid(friendRequestReqDto.getFriendUuid()).get())
                .friendUuid(uuid)
                .status(FriendType.RECEIVER)
                .build();

        friendRepository.save(friend2);

        return null;
    }
}
