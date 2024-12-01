package com.burglak.linker.service;

import com.burglak.linker.dto.FollowDto;
import com.burglak.linker.exception.FollowNotFoundException;
import com.burglak.linker.mapper.impl.FollowMapper;
import com.burglak.linker.model.Follow;
import com.burglak.linker.repository.FollowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class FollowService {

    private FollowRepository followRepository;

    private FollowMapper followMapper;

    public FollowService(FollowRepository followRepository, FollowMapper followMapper) {
        this.followRepository = followRepository;
        this.followMapper = followMapper;
    }

    public FollowDto createFollow(FollowDto followDto) {
        Follow follow = followMapper.mapFrom(followDto);
        Follow savedFollow = followRepository.save(follow);
        return followMapper.mapTo(savedFollow);
    }

    public FollowDto findFollowById(Long id) {
        Follow follow = followRepository.findById(id).orElseThrow(() -> new FollowNotFoundException(id));
        return followMapper.mapTo(follow);
    }

    public List<FollowDto> findAllFollows() {
        List<Follow> follows = StreamSupport.stream(followRepository.findAll().spliterator(), false).toList();
        List<FollowDto> followsDto = follows.stream().map(follow -> followMapper.mapTo(follow)).toList(); //throw FollowNotFoundException if follow does not exist
        return followsDto;
    }

    public FollowDto updateFollow(Long id, FollowDto followDto) {
        followDto.setId(id);

        Follow existingFollow = followRepository.findById(id).orElseThrow(() -> new FollowNotFoundException(id)); //throw FollowNotFoundException if follow does not exist
        existingFollow = followMapper.mapFrom(followDto);
        Follow savedFollow = followRepository.save(existingFollow);
        return followMapper.mapTo(savedFollow);
    }

    public FollowDto partialUpdateFollow(Long id, FollowDto followDto) {
        followDto.setId(id);

        //mapping dto to entity for easier updating fields
        Follow mappedFollow = followMapper.mapFrom(followDto);

        return followRepository.findById(id).map(existingFollow -> {
            //check if fields in followDto are not null, and if so, update the corresponding fields in the existing follow
            Optional.ofNullable(mappedFollow.getFollower()).ifPresent(existingFollow::setFollower);
            Optional.ofNullable(mappedFollow.getFollowed()).ifPresent(existingFollow::setFollowed);
            return followMapper.mapTo(followRepository.save(existingFollow));
        }).orElseThrow(() -> new FollowNotFoundException(id)); //throw FollowNotFoundException if follow does not exist
    }

    public void deleteFollow(Long id) {followRepository.deleteById(id);}

}
