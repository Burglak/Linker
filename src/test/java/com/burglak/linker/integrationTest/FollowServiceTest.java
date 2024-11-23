package com.burglak.linker.integrationTest;

import com.burglak.linker.dto.FollowDto;
import com.burglak.linker.dto.UserDto;
import com.burglak.linker.exception.FollowNotFoundException;
import com.burglak.linker.service.FollowService;
import com.burglak.linker.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static com.burglak.linker.TestDataUtil.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FollowServiceTest {

    private FollowService followService;
    private UserService userService;

    @Autowired
    public FollowServiceTest(FollowService followService, UserService userService) {
        this.followService = followService;
        this.userService = userService;
    }

    @Test
    public void testCreateFollow() {
        FollowDto followDto = followA();
        UserDto userA = userA();
        UserDto userB = userB();
        userService.createUser(userA);
        userService.createUser(userB);

        followDto.setFollower(userA);
        followDto.setFollowed(userB);

        FollowDto createdFollow = followService.createFollow(followDto);

        followDto.getFollowed().setCreatedAt(createdFollow.getFollowed().getCreatedAt());
        followDto.getFollower().setCreatedAt(createdFollow.getFollower().getCreatedAt());

        assertThat(createdFollow).isNotNull();

        assertThat(createdFollow.getFollower()).isEqualTo(followDto.getFollower());
        assertThat(createdFollow.getFollowed()).isEqualTo(followDto.getFollowed());
    }

    @Test
    public void testFindFollowById() {
        FollowDto followDto = followA();
        UserDto userA = userA();
        UserDto userB = userB();

        userService.createUser(userA);
        userService.createUser(userB);

        followDto.setFollower(userA);
        followDto.setFollowed(userB);

        FollowDto createdFollow = followService.createFollow(followDto);

        FollowDto foundFollow = followService.findFollowById(createdFollow.getId());

        followDto.getFollowed().setCreatedAt(foundFollow.getFollowed().getCreatedAt());
        followDto.getFollower().setCreatedAt(foundFollow.getFollower().getCreatedAt());

        assertThat(foundFollow).isNotNull();
        assertThat(foundFollow.getFollower()).isEqualTo(followDto.getFollower());
        assertThat(foundFollow.getFollowed()).isEqualTo(followDto.getFollowed());
    }

    @Test
    public void testFindAllFollows() {
        FollowDto followDtoA = followA();
        FollowDto followDtoB = followB();

        UserDto userA = userA();
        UserDto userB = userB();
        UserDto userC = userC();
        userService.createUser(userA);
        userService.createUser(userB);
        userService.createUser(userC);

        followService.createFollow(followDtoA);

        followService.createFollow(followDtoB);

        assertThat(followService.findAllFollows()).isNotNull();
        assertThat(followService.findAllFollows().size()).isEqualTo(2);

        FollowDto foundFollowA = followService.findFollowById(1L);
        FollowDto foundFollowB = followService.findFollowById(2L);

        assertThat(foundFollowA.getFollower().getFirstName()).isEqualTo(followDtoA.getFollower().getFirstName());
        assertThat(foundFollowB.getFollower().getFirstName()).isEqualTo(followDtoB.getFollower().getFirstName());
    }

    @Test
    public void testUpdateFollow() {
        FollowDto followDtoA = followA();
        FollowDto followDtoB = followB();

        UserDto userA = userA();
        UserDto userB = userB();
        UserDto userC = userC();

        userService.createUser(userA);
        userService.createUser(userB);
        userService.createUser(userC);

        followService.createFollow(followDtoA);

        FollowDto updatedFollow = followService.updateFollow(1L, followDtoB);

        assertThat(updatedFollow.getFollower().getFirstName()).isEqualTo(followDtoB.getFollower().getFirstName());
    }

    @Test
    public void testPartialUpdateFollow() {
        FollowDto followDto = followA();

        UserDto userA = userA();
        UserDto userB = userB();
        UserDto userC = userC();

        userService.createUser(userA);
        userService.createUser(userB);
        userService.createUser(userC);

        followDto.setFollowed(userA);
        followDto.setFollower(userB);

        followService.createFollow(followDto);

        FollowDto followToUpdate = FollowDto.builder().followed(userC).build();

        followService.partialUpdateFollow(1L, followToUpdate);

        FollowDto updatedUser = followService.findFollowById(1L);

        //make createdAt's value the same to pass the test. Fields createdAt are auto-filled while creating an entity
        updatedUser.getFollowed().setCreatedAt(followToUpdate.getFollowed().getCreatedAt());

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getFollowed()).isEqualTo(followToUpdate.getFollowed());

    }

    @Test void testDeleteFollow() {
        FollowDto followDto = followA();

        UserDto userA = userA();
        UserDto userB = userB();

        userService.createUser(userA);
        userService.createUser(userB);

        followService.createFollow(followDto);

        followService.deleteFollow(1L);

        assertThatThrownBy(() -> followService.findFollowById(1L)).isInstanceOf(FollowNotFoundException.class);
    }



}
