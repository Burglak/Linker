package com.burglak.linker;

import com.burglak.linker.dto.PostDto;
import com.burglak.linker.enums.PostVisibility;
import com.burglak.linker.mapper.impl.UserMapper;
import com.burglak.linker.model.User;
import com.burglak.linker.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    private UserRepository userRepository;

    private UserMapper userMapper;

    public Test(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void simulate() throws JsonProcessingException {
        User sender = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
        User recipient = userRepository.findById(2L).orElseThrow(() -> new RuntimeException("User not found"));

        PostDto postDto = PostDto.builder().
                user(userMapper.mapTo(sender)).
                upvotes(0L)
                .downvotes(0L)
                .postVisibility(PostVisibility.PUBLIC)
                .category(null)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(postDto);
        System.out.println(json);
    }

    @GetMapping("/test")
    public void test() throws JsonProcessingException {
        simulate();
    }


}
