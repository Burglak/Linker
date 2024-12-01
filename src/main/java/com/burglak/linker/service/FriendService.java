package com.burglak.linker.service;

import com.burglak.linker.dto.FriendDto;
import com.burglak.linker.exception.FriendNotFoundException;
import com.burglak.linker.mapper.impl.FriendMapper;
import com.burglak.linker.model.Friend;
import com.burglak.linker.repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class FriendService {

    private FriendRepository friendRepository;

    private FriendMapper friendMapper;

    public FriendService(FriendRepository friendRepository, FriendMapper friendMapper) {
        this.friendRepository = friendRepository;
        this.friendMapper = friendMapper;
    }

    public FriendDto createFriend(FriendDto friendDto) {
        Friend friend = friendMapper.mapFrom(friendDto);
        Friend savedFriend = friendRepository.save(friend);
        return friendMapper.mapTo(savedFriend);
    }

    public FriendDto findFriendById(Long id) {
        Friend friend = friendRepository.findById(id).orElseThrow(() -> new FriendNotFoundException(id));
        return friendMapper.mapTo(friend);
    }

    public List<FriendDto> findAllFriends() {
        List<Friend> friends = StreamSupport.stream(friendRepository.findAll().spliterator(), false).toList();
        List<FriendDto> friendsDto = friends.stream().map(friend -> friendMapper.mapTo(friend)).toList();
        return friendsDto;
    }

    public FriendDto updateFriend(Long id, FriendDto friendDto) {
        friendDto.setId(id);

        Friend existingFriend = friendRepository.findById(id).orElseThrow(() -> new FriendNotFoundException(id));
        existingFriend = friendMapper.mapFrom(friendDto);
        Friend savedFriend = friendRepository.save(existingFriend);
        return friendMapper.mapTo(savedFriend);
    }

    public FriendDto partialUpdateFriend(Long id, FriendDto friendDto) {
        friendDto.setId(id);

        Friend mappedFriend = friendMapper.mapFrom(friendDto);

        return friendRepository.findById(id).map(existingFriend -> {
            Optional.ofNullable(mappedFriend.getUserOne()).ifPresent(existingFriend::setUserOne);
            Optional.ofNullable(mappedFriend.getUserTwo()).ifPresent(existingFriend::setUserTwo);
            Optional.ofNullable(mappedFriend.getIsAccepted()).ifPresent(existingFriend::setIsAccepted);
            return friendMapper.mapTo(friendRepository.save(existingFriend));
        }).orElseThrow(() -> new FriendNotFoundException(id));
    }

    public void deleteFriend(Long id) {
        friendRepository.deleteById(id);
    }


}
