package com.burglak.linker.integrationTest;

import com.burglak.linker.TestDataUtil;
import com.burglak.linker.dto.UserDto;
import com.burglak.linker.exception.UserNotFoundException;
import com.burglak.linker.model.entity.User;
import com.burglak.linker.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {

    UserService userService;

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testCreateUser() {
        UserDto userDto = TestDataUtil.userA();

        UserDto createdUser = userService.createUser(userDto);

        assertThat(createdUser).isNotNull();
        
        assertThat(createdUser.getFirstName()).isEqualTo(userDto.getFirstName());
        assertThat(createdUser.getLastName()).isEqualTo(userDto.getLastName());
        assertThat(createdUser.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(createdUser.getPhone()).isEqualTo(userDto.getPhone());
        assertThat(createdUser.getPassword()).isEqualTo(userDto.getPassword());
        assertThat(createdUser.getBio()).isEqualTo(userDto.getBio());
        assertThat(createdUser.getProfilePicturePath()).isEqualTo(userDto.getProfilePicturePath());
        assertThat(createdUser.getUserRole()).isEqualTo(userDto.getUserRole());
        assertThat(createdUser.getIsActive()).isEqualTo(userDto.getIsActive());
    }
    
    @Test
    public void testFindUserById() {
        UserDto userDto = TestDataUtil.userA();
        
        userService.createUser(userDto);

        UserDto existingUser = userService.findUserById(1L);
        
        assertThat(existingUser).isNotNull();

        assertThat(existingUser.getFirstName()).isEqualTo(userDto.getFirstName());
        assertThat(existingUser.getLastName()).isEqualTo(userDto.getLastName());
        assertThat(existingUser.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(existingUser.getPhone()).isEqualTo(userDto.getPhone());
        assertThat(existingUser.getPassword()).isEqualTo(userDto.getPassword());
        assertThat(existingUser.getBio()).isEqualTo(userDto.getBio());
        assertThat(existingUser.getProfilePicturePath()).isEqualTo(userDto.getProfilePicturePath());
        assertThat(existingUser.getUserRole()).isEqualTo(userDto.getUserRole());
        assertThat(existingUser.getIsActive()).isEqualTo(userDto.getIsActive());
    }

    @Test
    public void testFindAllUsers() {
        UserDto userDtoA = TestDataUtil.userA();
        UserDto userDtoB = TestDataUtil.userB();

        userService.createUser(userDtoA);
        userService.createUser(userDtoB);

        assertThat(userService.findAllUsers()).isNotNull();
        assertThat(userService.findAllUsers().size()).isEqualTo(2);

        assertThat(userService.findAllUsers().get(0).getFirstName()).isEqualTo(userDtoA.getFirstName());
        assertThat(userService.findAllUsers().get(1).getFirstName()).isEqualTo(userDtoB.getFirstName());

        assertThat(userService.findAllUsers().get(0).getLastName()).isEqualTo(userDtoA.getLastName());
        assertThat(userService.findAllUsers().get(1).getLastName()).isEqualTo(userDtoB.getLastName());

        assertThat(userService.findAllUsers().get(0).getEmail()).isEqualTo(userDtoA.getEmail());
        assertThat(userService.findAllUsers().get(1).getEmail()).isEqualTo(userDtoB.getEmail());

        assertThat(userService.findAllUsers().get(0).getPhone()).isEqualTo(userDtoA.getPhone());
        assertThat(userService.findAllUsers().get(1).getPhone()).isEqualTo(userDtoB.getPhone());

        assertThat(userService.findAllUsers().get(0).getPassword()).isEqualTo(userDtoA.getPassword());
        assertThat(userService.findAllUsers().get(1).getPassword()).isEqualTo(userDtoB.getPassword());

        assertThat(userService.findAllUsers().get(0).getBio()).isEqualTo(userDtoA.getBio());
        assertThat(userService.findAllUsers().get(1).getBio()).isEqualTo(userDtoB.getBio());

        assertThat(userService.findAllUsers().get(0).getProfilePicturePath()).isEqualTo(userDtoA.getProfilePicturePath());
        assertThat(userService.findAllUsers().get(1).getProfilePicturePath()).isEqualTo(userDtoB.getProfilePicturePath());

        assertThat(userService.findAllUsers().get(0).getUserRole()).isEqualTo(userDtoA.getUserRole());
        assertThat(userService.findAllUsers().get(1).getUserRole()).isEqualTo(userDtoB.getUserRole());

        assertThat(userService.findAllUsers().get(0).getIsActive()).isEqualTo(userDtoA.getIsActive());
        assertThat(userService.findAllUsers().get(1).getIsActive()).isEqualTo(userDtoB.getIsActive());
    }

    @Test
    public void testUpdateUser() {
        UserDto userDto = TestDataUtil.userA();

        userService.createUser(userDto);

        UserDto newUserDto = TestDataUtil.userB();

        userService.updateUser(1L, newUserDto);

        UserDto existingUser = userService.findUserById(1L);

        assertThat(existingUser).isNotNull();

        assertThat(existingUser.getFirstName()).isEqualTo(newUserDto.getFirstName());
        assertThat(existingUser.getLastName()).isEqualTo(newUserDto.getLastName());
        assertThat(existingUser.getEmail()).isEqualTo(newUserDto.getEmail());
        assertThat(existingUser.getPhone()).isEqualTo(newUserDto.getPhone());
        assertThat(existingUser.getPassword()).isEqualTo(newUserDto.getPassword());
        assertThat(existingUser.getBio()).isEqualTo(newUserDto.getBio());
        assertThat(existingUser.getProfilePicturePath()).isEqualTo(newUserDto.getProfilePicturePath());
        assertThat(existingUser.getUserRole()).isEqualTo(newUserDto.getUserRole());
        assertThat(existingUser.getIsActive()).isEqualTo(newUserDto.getIsActive());
    }

    @Test
    public void testPartialUpdateUser() {
        UserDto userDto = TestDataUtil.userA();

        userService.createUser(userDto);

        String updatedName = "Updated Name";
        UserDto updatedUserDto = UserDto.builder().firstName(updatedName).build();

        userService.partialUpdateUser(1L, updatedUserDto);

        UserDto existingUser = userService.findUserById(1L);

        assertThat(existingUser).isNotNull();

        assertThat(existingUser.getFirstName()).isEqualTo(updatedName);
        assertThat(existingUser.getLastName()).isEqualTo(userDto.getLastName());
        assertThat(existingUser.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(existingUser.getPhone()).isEqualTo(userDto.getPhone());
        assertThat(existingUser.getPassword()).isEqualTo(userDto.getPassword());
        assertThat(existingUser.getBio()).isEqualTo(userDto.getBio());
        assertThat(existingUser.getProfilePicturePath()).isEqualTo(userDto.getProfilePicturePath());
        assertThat(existingUser.getUserRole()).isEqualTo(userDto.getUserRole());
        assertThat(existingUser.getIsActive()).isEqualTo(userDto.getIsActive());
    }

    @Test
    public void testDeleteUser(){
        UserDto userDto = TestDataUtil.userA();

        userService.createUser(userDto);

        userService.deleteUser(1L);

        assertThatThrownBy(() -> userService.findUserById(1L))
                .isInstanceOf(UserNotFoundException.class);
    }
}
