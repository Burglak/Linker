package com.burglak.linker.service;

import com.burglak.linker.dto.UserActivityDto;
import com.burglak.linker.dto.UserDto;
import com.burglak.linker.enums.UserActivityType;
import com.burglak.linker.exception.UserActivityNotFoundException;
import com.burglak.linker.mapper.impl.UserActivityMapper;
import com.burglak.linker.model.UserActivity;
import com.burglak.linker.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserActivityService {

    private final UserActivityRepository userActivityRepository;

    private final UserActivityMapper userActivityMapper;

    @Value("${application.user.activity.post-multiplier}")
    private  int postMultiplier;

    public UserActivityService(UserActivityRepository userActivityRepository, UserActivityMapper userActivityMapper) {
        this.userActivityRepository = userActivityRepository;
        this.userActivityMapper = userActivityMapper;
    }

    public UserActivityDto createUserActivity(UserActivityDto userActivityDto) {
        UserActivity userActivity = userActivityMapper.mapFrom(userActivityDto);
        UserActivity savedUserActivity = userActivityRepository.save(userActivity);
        return userActivityMapper.mapTo(savedUserActivity);
    }

    public UserActivityDto findUserActivityById(Long id) {
        UserActivity userActivity = userActivityRepository.findById(id)
                .orElseThrow(() -> new UserActivityNotFoundException(id));
        return userActivityMapper.mapTo(userActivity);
    }

    public List<UserActivityDto> findAllUserActivities() {
        List<UserActivity> activities = StreamSupport
                .stream(userActivityRepository.findAll().spliterator(), false)
                .toList();
        return activities.stream()
                .map(userActivityMapper::mapTo)
                .toList();
    }

    public List<UserActivityDto> findAllUserActivitiesByUserId(Long userId) {
        List<UserActivity> activities = userActivityRepository.findAllByUser_Id(userId);
        return activities.stream()
                .map(userActivityMapper::mapTo)
                .toList();
    }

    //increase messagesSent or postsCreated depending on activity type
    public UserActivityDto updateUserActivity(UserDto userDto, UserActivityType activityType) {
        UserActivity existingUserActivity = null;
        List<UserActivity> userActivities = userActivityRepository.findAllByUser_Id(userDto.getId());
        for(UserActivity activity : userActivities) {
            if(activity.getActivityDate().toLocalDateTime().toLocalDate().equals(LocalDate.now())){
                existingUserActivity = activity;
                break;
            }
        }
        if(existingUserActivity == null) {
            UserActivityDto createdActivity = createUserActivity(UserActivityDto.builder().user(userDto).build());
            existingUserActivity = userActivityMapper.mapFrom(createdActivity);
        }

        switch (activityType) {
            case MESSAGE:
                existingUserActivity.setMessagesSent(existingUserActivity.getMessagesSent() + 1);
                break;
            case POST:
                existingUserActivity.setPostsCreated(existingUserActivity.getPostsCreated() + 1);
                break;
            default:
                throw new IllegalArgumentException("Unsupported activity type");
        }
        existingUserActivity.setTotalActivity(
                calculateTotalActivity(
                        existingUserActivity.getMessagesSent(),
                        existingUserActivity.getPostsCreated()
                )
        );
        UserActivityDto savedUserActivity = userActivityMapper.mapTo(userActivityRepository.save(existingUserActivity));
        return savedUserActivity;
    }


    public void deleteUserActivity(Long id) {
        userActivityRepository.deleteById(id);
    }

    private int calculateTotalActivity(int messagesSent, int postsCreated) {
        return messagesSent + (postsCreated * postMultiplier);
    }

}
