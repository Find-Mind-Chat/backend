package com.example.backend.login.service;

import com.example.backend.global.GlobalException;
import com.example.backend.global.ResponseStatus;
import com.example.backend.login.domain.Friend;
import com.example.backend.login.domain.Member;
import com.example.backend.login.dto.req.FriendUuidReqDto;
import com.example.backend.login.dto.res.FriendInfoResDto;
import com.example.backend.login.enums.FriendType;
import com.example.backend.login.repository.FriendRepository;
import com.example.backend.login.repository.MemberRespository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FriendSerivceImp implements FriendService {

    private final FriendRepository friendRepository;
    private final MemberRespository memberRespository;


    @Override
    public List<FriendInfoResDto> friendList(String uuid, String type) {


        List<Friend> friendList = friendRepository.findByUuidAndStatus(uuid,
        type.equals("all") ? FriendType.FRIEND : type.equals("send") ? FriendType.SENDER : type.equals("receive") ? FriendType.RECEIVER: null);

        return friendList.stream().map(friend -> FriendInfoResDto.EntityToDto(friend.getFriend()))
                .toList();

    }

    @Override
    public Void friendRequest(String uuid, FriendUuidReqDto friendUuidReqDto) {

        Friend friend = Friend.builder()
                .uuid(uuid)
                .friend(memberRespository.findByUuid(friendUuidReqDto.getFriendUuid()))
                .status(FriendType.SENDER)
                .build();

        friendRepository.save(friend);

        //친구 객체
        Friend friend2 = Friend.builder()
                .uuid(friendUuidReqDto.getFriendUuid())
                .friend(memberRespository.findByUuid(uuid))
                .status(FriendType.RECEIVER)
                .build();

        friendRepository.save(friend2);

        return null;
    }

    @Override
    public Void friendDelete(String uuid, FriendUuidReqDto friendUuidReqDto) {

        Member member = memberRespository.findByUuid(friendUuidReqDto.getFriendUuid());

        Friend friend = friendRepository.findByUuidAndFriend(uuid, member)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_FOUND_MEMBER));

        friendRepository.delete(friend);

        Friend friend2 = friendRepository.findByUuidAndFriend(member.getUuid(),memberRespository.findByUuid(uuid))
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_FOUND_MEMBER));

        friendRepository.delete(friend2);

        return null;
    }

}
