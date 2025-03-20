package com.example.backend.login.service;

import com.example.backend.global.GlobalException;
import com.example.backend.global.ResponseStatus;
import com.example.backend.login.domain.Friend;
import com.example.backend.login.domain.Member;
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

        List<Friend> friendList = friendRepository.findByMember(member);
        List<String> friendUuidList = new ArrayList<>();

        //친구 리스트
        if (type.equals("all")){

            for (Friend friend : friendList){
                if (friend.getStatus() == FriendType.FRIEND)
                    friendUuidList.add(friend.getFriendUuid());
            }
        }
        //내가 보낸 요청 리스트
        else if (type.equals("request")){

            for (Friend friend : friendList){
                if (friend.getStatus() == FriendType.SENDER){
                    friendUuidList.add(friend.getFriendUuid());
                }
            }
        }
        //내가 받은 요청 리스트
        else if (type.equals("response")){
            for (Friend friend : friendList){
                if (friend.getStatus() == FriendType.SENDER){
                    friendUuidList.add(friend.getFriendUuid());
                }
            }
        }
        return friendUuidList;
    }
}
