package com.burglak.linker.unitTest;

import com.burglak.linker.TestDataUtil;
import com.burglak.linker.mapper.*;
import com.burglak.linker.model.entity.*;
import com.burglak.linker.dto.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MapperUnitTest {

    private Mapper<Follow, FollowDto> followMapper;

    private Mapper<Friend, FriendDto> friendMapper;

    private Mapper<Message, MessageDto> messageMapper;

    private Mapper<PostCategory, PostCategoryDto> postCategoryMapper;

    private Mapper<PostComment, PostCommentDto> postCommentMapper;

    private Mapper<Post, PostDto> postMapper;

    private Mapper<PostImage, PostImageDto> postImageMapper;

    private Mapper<PostReport, PostReportDto> postReportMapper;

    private Mapper<PostReaction, PostReactionDto> postReactionMapper;

    private Mapper<User, UserDto> userMapper;

    private Mapper<UserImage, UserImageDto> userImageMapper;

    private Mapper<UserSettings, UserSettingsDto> userSettingsMapper;

    private Mapper<UserStatus, UserStatusDto> userStatusMapper;

    private Mapper<UserSavedPost, UserSavedPostDto> userSavedPostMapper;

    @Autowired
    public MapperUnitTest(Mapper<Follow, FollowDto> followMapper, Mapper<Friend, FriendDto> friendMapper, Mapper<Message, MessageDto> messageMapper, Mapper<PostCategory, PostCategoryDto> postCategoryMapper, Mapper<PostComment, PostCommentDto> postCommentMapper, Mapper<Post, PostDto> postMapper, Mapper<PostImage, PostImageDto> postImageMapper, Mapper<PostReport, PostReportDto> postReportMapper, Mapper<PostReaction, PostReactionDto> postReactionMapper, Mapper<User, UserDto> userMapper, Mapper<UserImage, UserImageDto> userImageMapper, Mapper<UserSettings, UserSettingsDto> userSettingsMapper, Mapper<UserStatus, UserStatusDto> userStatusMapper, Mapper<UserSavedPost, UserSavedPostDto> userSavedPostMapper) {
        this.followMapper = followMapper;
        this.friendMapper = friendMapper;
        this.messageMapper = messageMapper;
        this.postCategoryMapper = postCategoryMapper;
        this.postCommentMapper = postCommentMapper;
        this.postMapper = postMapper;
        this.postImageMapper = postImageMapper;
        this.postReportMapper = postReportMapper;
        this.postReactionMapper = postReactionMapper;
        this.userMapper = userMapper;
        this.userImageMapper = userImageMapper;
        this.userSettingsMapper = userSettingsMapper;
        this.userStatusMapper = userStatusMapper;
        this.userSavedPostMapper = userSavedPostMapper;
    }

    @Test
    public void followMapperTest() {
        //prepare test data
        FollowDto followDto = TestDataUtil.followA();

        //map from dto to entity
        Follow followEntity = followMapper.mapFrom(followDto);

        //assertions on entity after mapping
        assertThat(followEntity).isNotNull();
        assertThat(followEntity.getId()).isEqualTo(followDto.getId());

        //check fields of follower (User)
        User followerEntity = followEntity.getFollower();
        UserDto followerDto = followDto.getFollower();

        assertThat(followerEntity.getId()).isEqualTo(followerDto.getId());
        assertThat(followerEntity.getFirstName()).isEqualTo(followerDto.getFirstName());
        assertThat(followerEntity.getLastName()).isEqualTo(followerDto.getLastName());
        assertThat(followerEntity.getEmail()).isEqualTo(followerDto.getEmail());
        assertThat(followerEntity.getPhone()).isEqualTo(followerDto.getPhone());
        assertThat(followerEntity.getPassword()).isEqualTo(followerDto.getPassword());
        assertThat(followerEntity.getBio()).isEqualTo(followerDto.getBio());
        assertThat(followerEntity.getProfilePicturePath()).isEqualTo(followerDto.getProfilePicturePath());
        assertThat(followerEntity.getUserRole()).isEqualTo(followerDto.getUserRole());
        assertThat(followerEntity.getIsActive()).isEqualTo(followerDto.getIsActive());
        assertThat(followerEntity.getCreatedAt()).isEqualTo(followerDto.getCreatedAt());
        assertThat(followerEntity.getLastLogin()).isEqualTo(followerDto.getLastLogin());

        //check fields of followed (User)
        User followedEntity = followEntity.getFollowed();
        UserDto followedDto = followDto.getFollowed();

        assertThat(followedEntity.getId()).isEqualTo(followedDto.getId());
        assertThat(followedEntity.getFirstName()).isEqualTo(followedDto.getFirstName());
        assertThat(followedEntity.getLastName()).isEqualTo(followedDto.getLastName());
        assertThat(followedEntity.getEmail()).isEqualTo(followedDto.getEmail());
        assertThat(followedEntity.getPhone()).isEqualTo(followedDto.getPhone());
        assertThat(followedEntity.getPassword()).isEqualTo(followedDto.getPassword());
        assertThat(followedEntity.getBio()).isEqualTo(followedDto.getBio());
        assertThat(followedEntity.getProfilePicturePath()).isEqualTo(followedDto.getProfilePicturePath());
        assertThat(followedEntity.getUserRole()).isEqualTo(followedDto.getUserRole());
        assertThat(followedEntity.getIsActive()).isEqualTo(followedDto.getIsActive());
        assertThat(followedEntity.getCreatedAt()).isEqualTo(followedDto.getCreatedAt());
        assertThat(followedEntity.getLastLogin()).isEqualTo(followedDto.getLastLogin());

        //assert creation date
        assertThat(followEntity.getCreatedAt()).isEqualTo(followDto.getCreatedAt());

        //map back from entity to dto
        FollowDto mappedBackFollowDto = followMapper.mapTo(followEntity);

        //assertions on dto after reverse mapping
        assertThat(mappedBackFollowDto).isNotNull();
        assertThat(mappedBackFollowDto.getId()).isEqualTo(followEntity.getId());

        //re-check follower fields after reverse mapping
        UserDto mappedFollowerDto = mappedBackFollowDto.getFollower();
        assertThat(mappedFollowerDto.getId()).isEqualTo(followerEntity.getId());
        assertThat(mappedFollowerDto.getFirstName()).isEqualTo(followerEntity.getFirstName());
        assertThat(mappedFollowerDto.getLastName()).isEqualTo(followerEntity.getLastName());
        assertThat(mappedFollowerDto.getEmail()).isEqualTo(followerEntity.getEmail());
        assertThat(mappedFollowerDto.getPhone()).isEqualTo(followerEntity.getPhone());
        assertThat(mappedFollowerDto.getPassword()).isEqualTo(followerEntity.getPassword());
        assertThat(mappedFollowerDto.getBio()).isEqualTo(followerEntity.getBio());
        assertThat(mappedFollowerDto.getProfilePicturePath()).isEqualTo(followerEntity.getProfilePicturePath());
        assertThat(mappedFollowerDto.getUserRole()).isEqualTo(followerEntity.getUserRole());
        assertThat(mappedFollowerDto.getIsActive()).isEqualTo(followerEntity.getIsActive());
        assertThat(mappedFollowerDto.getCreatedAt()).isEqualTo(followerEntity.getCreatedAt());
        assertThat(mappedFollowerDto.getLastLogin()).isEqualTo(followerEntity.getLastLogin());

        //re-check followed fields after reverse mapping
        UserDto mappedFollowedDto = mappedBackFollowDto.getFollowed();
        assertThat(mappedFollowedDto.getId()).isEqualTo(followedEntity.getId());
        assertThat(mappedFollowedDto.getFirstName()).isEqualTo(followedEntity.getFirstName());
        assertThat(mappedFollowedDto.getLastName()).isEqualTo(followedEntity.getLastName());
        assertThat(mappedFollowedDto.getEmail()).isEqualTo(followedEntity.getEmail());
        assertThat(mappedFollowedDto.getPhone()).isEqualTo(followedEntity.getPhone());
        assertThat(mappedFollowedDto.getPassword()).isEqualTo(followedEntity.getPassword());
        assertThat(mappedFollowedDto.getBio()).isEqualTo(followedEntity.getBio());
        assertThat(mappedFollowedDto.getProfilePicturePath()).isEqualTo(followedEntity.getProfilePicturePath());
        assertThat(mappedFollowedDto.getUserRole()).isEqualTo(followedEntity.getUserRole());
        assertThat(mappedFollowedDto.getIsActive()).isEqualTo(followedEntity.getIsActive());
        assertThat(mappedFollowedDto.getCreatedAt()).isEqualTo(followedEntity.getCreatedAt());
        assertThat(mappedFollowedDto.getLastLogin()).isEqualTo(followedEntity.getLastLogin());

        //check creation date after reverse mapping
        assertThat(mappedBackFollowDto.getCreatedAt()).isEqualTo(followEntity.getCreatedAt());
    }

    @Test
    public void friendMapperTest() {
        //prepare test data
        FriendDto friendDto = TestDataUtil.friendA();

        //map from dto to entity
        Friend friendEntity = friendMapper.mapFrom(friendDto);

        //assertions on entity after mapping
        assertThat(friendEntity).isNotNull();
        assertThat(friendEntity.getId()).isEqualTo(friendDto.getId());
        assertThat(friendEntity.getIsAccepted()).isEqualTo(friendDto.getIsAccepted());
        assertThat(friendEntity.getCreatedAt()).isEqualTo(friendDto.getCreatedAt());

        //check userOne fields
        User userOneEntity = friendEntity.getUserOne();
        UserDto userOneDto = friendDto.getUserOne();

        assertThat(userOneEntity.getId()).isEqualTo(userOneDto.getId());
        assertThat(userOneEntity.getFirstName()).isEqualTo(userOneDto.getFirstName());
        assertThat(userOneEntity.getLastName()).isEqualTo(userOneDto.getLastName());
        assertThat(userOneEntity.getEmail()).isEqualTo(userOneDto.getEmail());
        assertThat(userOneEntity.getPhone()).isEqualTo(userOneDto.getPhone());

        //check userTwo fields
        User userTwoEntity = friendEntity.getUserTwo();
        UserDto userTwoDto = friendDto.getUserTwo();

        assertThat(userTwoEntity.getId()).isEqualTo(userTwoDto.getId());
        assertThat(userTwoEntity.getFirstName()).isEqualTo(userTwoDto.getFirstName());
        assertThat(userTwoEntity.getLastName()).isEqualTo(userTwoDto.getLastName());
        assertThat(userTwoEntity.getEmail()).isEqualTo(userTwoDto.getEmail());

        //map back from entity to dto
        FriendDto mappedBackFriendDto = friendMapper.mapTo(friendEntity);

        //assertions on dto after reverse mapping
        assertThat(mappedBackFriendDto).isNotNull();
        assertThat(mappedBackFriendDto.getId()).isEqualTo(friendEntity.getId());
        assertThat(mappedBackFriendDto.getIsAccepted()).isEqualTo(friendEntity.getIsAccepted());
        assertThat(mappedBackFriendDto.getCreatedAt()).isEqualTo(friendEntity.getCreatedAt());

        //re-check userOne fields
        UserDto mappedUserOneDto = mappedBackFriendDto.getUserOne();
        assertThat(mappedUserOneDto.getId()).isEqualTo(userOneEntity.getId());

        //re-check userTwo fields
        UserDto mappedUserTwoDto = mappedBackFriendDto.getUserTwo();
        assertThat(mappedUserTwoDto.getId()).isEqualTo(userTwoEntity.getId());
    }

    @Test
    public void messageMapperTest() {
        //prepare test data
        MessageDto messageDto = TestDataUtil.messageA();

        //map from dto to entity
        Message messageEntity = messageMapper.mapFrom(messageDto);

        //assertions on entity after mapping
        assertThat(messageEntity).isNotNull();
        assertThat(messageEntity.getId()).isEqualTo(messageDto.getId());
        assertThat(messageEntity.getContent()).isEqualTo(messageDto.getContent());
        assertThat(messageEntity.getIsRead()).isEqualTo(messageDto.getIsRead());
        assertThat(messageEntity.getCreatedAt()).isEqualTo(messageDto.getCreatedAt());

        //check sender and recipient fields
        User senderEntity = messageEntity.getSender();
        UserDto senderDto = messageDto.getSender();
        assertThat(senderEntity.getId()).isEqualTo(senderDto.getId());

        User recipientEntity = messageEntity.getRecipient();
        UserDto recipientDto = messageDto.getRecipient();
        assertThat(recipientEntity.getId()).isEqualTo(recipientDto.getId());

        //map back from entity to dto
        MessageDto mappedBackMessageDto = messageMapper.mapTo(messageEntity);

        //assertions on dto after reverse mapping
        assertThat(mappedBackMessageDto).isNotNull();
        assertThat(mappedBackMessageDto.getId()).isEqualTo(messageEntity.getId());
        assertThat(mappedBackMessageDto.getContent()).isEqualTo(messageEntity.getContent());
        assertThat(mappedBackMessageDto.getIsRead()).isEqualTo(messageEntity.getIsRead());
        assertThat(mappedBackMessageDto.getCreatedAt()).isEqualTo(messageEntity.getCreatedAt());

        //re-check sender and recipient fields
        UserDto mappedSenderDto = mappedBackMessageDto.getSender();
        assertThat(mappedSenderDto.getId()).isEqualTo(senderEntity.getId());

        UserDto mappedRecipientDto = mappedBackMessageDto.getRecipient();
        assertThat(mappedRecipientDto.getId()).isEqualTo(recipientEntity.getId());
    }

    @Test
    public void postMapperTest() {
        //prepare test data
        PostDto postDto = TestDataUtil.postA();

        //map from dto to entity
        Post postEntity = postMapper.mapFrom(postDto);

        //assertions on entity after mapping
        assertThat(postEntity).isNotNull();
        assertThat(postEntity.getId()).isEqualTo(postDto.getId());
        assertThat(postEntity.getUpvotes()).isEqualTo(postDto.getUpvotes());
        assertThat(postEntity.getDownvotes()).isEqualTo(postDto.getDownvotes());
        assertThat(postEntity.getCreatedAt()).isEqualTo(postDto.getCreatedAt());

        //check user fields
        User userEntity = postEntity.getUser();
        UserDto userDto = postDto.getUser();
        assertThat(userEntity.getId()).isEqualTo(userDto.getId());

        //check post category fields
        PostCategory postCategoryEntity = postEntity.getCategory();
        PostCategoryDto postCategoryDto = postDto.getCategory();
        assertThat(postCategoryEntity.getId()).isEqualTo(postCategoryDto.getId());

        //map back from entity to dto
        PostDto mappedBackPostDto = postMapper.mapTo(postEntity);

        //assertions on dto after reverse mapping
        assertThat(mappedBackPostDto).isNotNull();
        assertThat(mappedBackPostDto.getId()).isEqualTo(postEntity.getId());
        assertThat(mappedBackPostDto.getUpvotes()).isEqualTo(postEntity.getUpvotes());
        assertThat(mappedBackPostDto.getDownvotes()).isEqualTo(postEntity.getDownvotes());

        //re-check user and category fields
        UserDto mappedUserDto = mappedBackPostDto.getUser();
        assertThat(mappedUserDto.getId()).isEqualTo(userEntity.getId());

        PostCategoryDto mappedCategoryDto = mappedBackPostDto.getCategory();
        assertThat(mappedCategoryDto.getId()).isEqualTo(postCategoryEntity.getId());
    }

    @Test
    public void postCommentMapperTest() {
        //prepare test data
        PostCommentDto postCommentDto = TestDataUtil.postCommentA();

        //map from dto to entity
        PostComment postCommentEntity = postCommentMapper.mapFrom(postCommentDto);

        //assertions on entity after mapping
        assertThat(postCommentEntity).isNotNull();
        assertThat(postCommentEntity.getId()).isEqualTo(postCommentDto.getId());
        assertThat(postCommentEntity.getContent()).isEqualTo(postCommentDto.getContent());
        assertThat(postCommentEntity.getCreatedAt()).isEqualTo(postCommentDto.getCreatedAt());

        //check post mapping
        Post postEntity = postCommentEntity.getPost();
        PostDto postDto = postCommentDto.getPost();
        assertThat(postEntity.getId()).isEqualTo(postDto.getId());

        //check user mapping
        User userEntity = postCommentEntity.getUser();
        UserDto userDto = postCommentDto.getUser();
        assertThat(userEntity.getId()).isEqualTo(userDto.getId());

        //map back from entity to dto
        PostCommentDto mappedBackPostCommentDto = postCommentMapper.mapTo(postCommentEntity);

        //assertions on dto after reverse mapping
        assertThat(mappedBackPostCommentDto).isNotNull();
        assertThat(mappedBackPostCommentDto.getId()).isEqualTo(postCommentEntity.getId());
        assertThat(mappedBackPostCommentDto.getContent()).isEqualTo(postCommentEntity.getContent());
        assertThat(mappedBackPostCommentDto.getCreatedAt()).isEqualTo(postCommentEntity.getCreatedAt());

        //re-check post and user mapping after reverse mapping
        PostDto mappedPostDto = mappedBackPostCommentDto.getPost();
        assertThat(mappedPostDto.getId()).isEqualTo(postEntity.getId());

        UserDto mappedUserDto = mappedBackPostCommentDto.getUser();
        assertThat(mappedUserDto.getId()).isEqualTo(userEntity.getId());
    }

    @Test
    public void postReactionMapperTest() {
        //prepare test data
        PostReactionDto postReactionDto = TestDataUtil.postReactionA();

        //map from dto to entity
        PostReaction postReactionEntity = postReactionMapper.mapFrom(postReactionDto);

        //assertions on entity after mapping
        assertThat(postReactionEntity).isNotNull();
        assertThat(postReactionEntity.getId()).isEqualTo(postReactionDto.getId());
        assertThat(postReactionEntity.getPostReactionType()).isEqualTo(postReactionDto.getPostReactionType());
        assertThat(postReactionEntity.getCreatedAt()).isEqualTo(postReactionDto.getCreatedAt());

        //check post mapping
        Post postEntity = postReactionEntity.getPost();
        PostDto postDto = postReactionDto.getPost();
        assertThat(postEntity.getId()).isEqualTo(postDto.getId());

        //check user mapping
        User userEntity = postReactionEntity.getUser();
        UserDto userDto = postReactionDto.getUser();
        assertThat(userEntity.getId()).isEqualTo(userDto.getId());

        //map back from entity to dto
        PostReactionDto mappedBackPostReactionDto = postReactionMapper.mapTo(postReactionEntity);

        //assertions on dto after reverse mapping
        assertThat(mappedBackPostReactionDto).isNotNull();
        assertThat(mappedBackPostReactionDto.getId()).isEqualTo(postReactionEntity.getId());
        assertThat(mappedBackPostReactionDto.getPostReactionType()).isEqualTo(postReactionEntity.getPostReactionType());
        assertThat(mappedBackPostReactionDto.getCreatedAt()).isEqualTo(postReactionEntity.getCreatedAt());

        //re-check post and user mapping after reverse mapping
        PostDto mappedPostDto = mappedBackPostReactionDto.getPost();
        assertThat(mappedPostDto.getId()).isEqualTo(postEntity.getId());

        UserDto mappedUserDto = mappedBackPostReactionDto.getUser();
        assertThat(mappedUserDto.getId()).isEqualTo(userEntity.getId());
    }

    @Test
    public void userSettingsMapperTest() {
        //prepare test data
        UserSettingsDto userSettingsDto = TestDataUtil.userSettingsA();

        //map from dto to entity
        UserSettings userSettingsEntity = userSettingsMapper.mapFrom(userSettingsDto);

        //assertions on entity after mapping
        assertThat(userSettingsEntity).isNotNull();
        assertThat(userSettingsEntity.getId()).isEqualTo(userSettingsDto.getId());
        assertThat(userSettingsEntity.getShowFriends()).isEqualTo(userSettingsDto.getShowFriends());
        assertThat(userSettingsEntity.getShowProfilePicture()).isEqualTo(userSettingsDto.getShowProfilePicture());
        assertThat(userSettingsEntity.getShowBio()).isEqualTo(userSettingsDto.getShowBio());
        assertThat(userSettingsEntity.getShowPosts()).isEqualTo(userSettingsDto.getShowPosts());
        assertThat(userSettingsEntity.getShowLikes()).isEqualTo(userSettingsDto.getShowLikes());
        assertThat(userSettingsEntity.getShowComments()).isEqualTo(userSettingsDto.getShowComments());
        assertThat(userSettingsEntity.getCreatedAt()).isEqualTo(userSettingsDto.getCreatedAt());
        assertThat(userSettingsEntity.getUpdatedAt()).isEqualTo(userSettingsDto.getUpdatedAt());

        //check user mapping
        User userEntity = userSettingsEntity.getUser();
        UserDto userDto = userSettingsDto.getUser();
        assertThat(userEntity.getId()).isEqualTo(userDto.getId());

        //map back from entity to dto
        UserSettingsDto mappedBackUserSettingsDto = userSettingsMapper.mapTo(userSettingsEntity);

        //assertions on dto after reverse mapping
        assertThat(mappedBackUserSettingsDto).isNotNull();
        assertThat(mappedBackUserSettingsDto.getId()).isEqualTo(userSettingsEntity.getId());
        assertThat(mappedBackUserSettingsDto.getShowFriends()).isEqualTo(userSettingsEntity.getShowFriends());
        assertThat(mappedBackUserSettingsDto.getShowProfilePicture()).isEqualTo(userSettingsEntity.getShowProfilePicture());
        assertThat(mappedBackUserSettingsDto.getShowBio()).isEqualTo(userSettingsEntity.getShowBio());
        assertThat(mappedBackUserSettingsDto.getShowPosts()).isEqualTo(userSettingsEntity.getShowPosts());
        assertThat(mappedBackUserSettingsDto.getShowLikes()).isEqualTo(userSettingsEntity.getShowLikes());
        assertThat(mappedBackUserSettingsDto.getShowComments()).isEqualTo(userSettingsEntity.getShowComments());
        assertThat(mappedBackUserSettingsDto.getCreatedAt()).isEqualTo(userSettingsEntity.getCreatedAt());
        assertThat(mappedBackUserSettingsDto.getUpdatedAt()).isEqualTo(userSettingsEntity.getUpdatedAt());

        //re-check user mapping after reverse mapping
        UserDto mappedUserDto = mappedBackUserSettingsDto.getUser();
        assertThat(mappedUserDto.getId()).isEqualTo(userEntity.getId());
    }

    @Test
    public void postImageMapperTest() {
        //prepare test data
        PostImageDto postImageDto = TestDataUtil.postImageA();

        //map from dto to entity
        PostImage postImageEntity = postImageMapper.mapFrom(postImageDto);

        //assertions on entity after mapping
        assertThat(postImageEntity).isNotNull();
        assertThat(postImageEntity.getId()).isEqualTo(postImageDto.getId());
        assertThat(postImageEntity.getImagePath()).isEqualTo(postImageDto.getImagePath());
        assertThat(postImageEntity.getIsPrimary()).isEqualTo(postImageDto.getIsPrimary());
        assertThat(postImageEntity.getCreatedAt()).isEqualTo(postImageDto.getCreatedAt());

        //check post mapping
        Post postEntity = postImageEntity.getPost();
        PostDto postDto = postImageDto.getPost();
        assertThat(postEntity.getId()).isEqualTo(postDto.getId());

        //map back from entity to dto
        PostImageDto mappedBackPostImageDto = postImageMapper.mapTo(postImageEntity);

        //assertions on dto after reverse mapping
        assertThat(mappedBackPostImageDto).isNotNull();
        assertThat(mappedBackPostImageDto.getId()).isEqualTo(postImageEntity.getId());
        assertThat(mappedBackPostImageDto.getImagePath()).isEqualTo(postImageEntity.getImagePath());
        assertThat(mappedBackPostImageDto.getIsPrimary()).isEqualTo(postImageEntity.getIsPrimary());
        assertThat(mappedBackPostImageDto.getCreatedAt()).isEqualTo(postImageEntity.getCreatedAt());

        //re-check post mapping after reverse mapping
        PostDto mappedPostDto = mappedBackPostImageDto.getPost();
        assertThat(mappedPostDto.getId()).isEqualTo(postEntity.getId());
    }

    @Test
    public void userStatusMapperTest() {
        //prepare test data
        UserStatusDto userStatusDto = TestDataUtil.userStatusA();

        //map from dto to entity
        UserStatus userStatusEntity = userStatusMapper.mapFrom(userStatusDto);

        //assertions on entity after mapping
        assertThat(userStatusEntity).isNotNull();
        assertThat(userStatusEntity.getId()).isEqualTo(userStatusDto.getId());
        assertThat(userStatusEntity.getIsSuspended()).isEqualTo(userStatusDto.getIsSuspended());
        assertThat(userStatusEntity.getIsVerified()).isEqualTo(userStatusDto.getIsVerified());
        assertThat(userStatusEntity.getSuspendedUntil()).isEqualTo(userStatusDto.getSuspendedUntil());

        //check user mapping
        User userEntity = userStatusEntity.getUser();
        UserDto userDto = userStatusDto.getUser();
        assertThat(userEntity.getId()).isEqualTo(userDto.getId());

        //map back from entity to dto
        UserStatusDto mappedBackUserStatusDto = userStatusMapper.mapTo(userStatusEntity);

        //assertions on dto after reverse mapping
        assertThat(mappedBackUserStatusDto).isNotNull();
        assertThat(mappedBackUserStatusDto.getId()).isEqualTo(userStatusEntity.getId());
        assertThat(mappedBackUserStatusDto.getIsSuspended()).isEqualTo(userStatusEntity.getIsSuspended());
        assertThat(mappedBackUserStatusDto.getIsVerified()).isEqualTo(userStatusEntity.getIsVerified());
        assertThat(mappedBackUserStatusDto.getSuspendedUntil()).isEqualTo(userStatusEntity.getSuspendedUntil());

        //re-check user mapping after reverse mapping
        UserDto mappedUserDto = mappedBackUserStatusDto.getUser();
        assertThat(mappedUserDto.getId()).isEqualTo(userEntity.getId());
    }

    @Test
    public void userMapperTest() {
        //prepare test data
        UserDto userDto = TestDataUtil.userA();

        //map from dto to entity
        User userEntity = userMapper.mapFrom(userDto);

        //assertions on entity after mapping
        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getId()).isEqualTo(userDto.getId());
        assertThat(userEntity.getFirstName()).isEqualTo(userDto.getFirstName());
        assertThat(userEntity.getLastName()).isEqualTo(userDto.getLastName());
        assertThat(userEntity.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(userEntity.getPhone()).isEqualTo(userDto.getPhone());
        assertThat(userEntity.getPassword()).isEqualTo(userDto.getPassword());
        assertThat(userEntity.getBio()).isEqualTo(userDto.getBio());
        assertThat(userEntity.getProfilePicturePath()).isEqualTo(userDto.getProfilePicturePath());
        assertThat(userEntity.getUserRole()).isEqualTo(userDto.getUserRole());
        assertThat(userEntity.getIsActive()).isEqualTo(userDto.getIsActive());
        assertThat(userEntity.getCreatedAt()).isEqualTo(userDto.getCreatedAt());
        assertThat(userEntity.getLastLogin()).isEqualTo(userDto.getLastLogin());

        //map back from entity to dto
        UserDto mappedBackUserDto = userMapper.mapTo(userEntity);

        //assertions on dto after reverse mapping
        assertThat(mappedBackUserDto).isNotNull();
        assertThat(mappedBackUserDto.getId()).isEqualTo(userEntity.getId());
        assertThat(mappedBackUserDto.getFirstName()).isEqualTo(userEntity.getFirstName());
        assertThat(mappedBackUserDto.getLastName()).isEqualTo(userEntity.getLastName());
        assertThat(mappedBackUserDto.getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(mappedBackUserDto.getPhone()).isEqualTo(userEntity.getPhone());
        assertThat(mappedBackUserDto.getPassword()).isEqualTo(userEntity.getPassword());
        assertThat(mappedBackUserDto.getBio()).isEqualTo(userEntity.getBio());
        assertThat(mappedBackUserDto.getProfilePicturePath()).isEqualTo(userEntity.getProfilePicturePath());
        assertThat(mappedBackUserDto.getUserRole()).isEqualTo(userEntity.getUserRole());
        assertThat(mappedBackUserDto.getIsActive()).isEqualTo(userEntity.getIsActive());
        assertThat(mappedBackUserDto.getCreatedAt()).isEqualTo(userEntity.getCreatedAt());
        assertThat(mappedBackUserDto.getLastLogin()).isEqualTo(userEntity.getLastLogin());
    }

    @Test
    public void userImageMapperTest() {
        //prepare test data
        UserImageDto userImageDto = TestDataUtil.userImageA();

        //map from dto to entity
        UserImage userImageEntity = userImageMapper.mapFrom(userImageDto);

        //assertions on entity after mapping
        assertThat(userImageEntity).isNotNull();
        assertThat(userImageEntity.getId()).isEqualTo(userImageDto.getId());
        assertThat(userImageEntity.getPath()).isEqualTo(userImageDto.getPath());
        assertThat(userImageEntity.getCreatedAt()).isEqualTo(userImageDto.getCreatedAt());

        //check user mapping
        User userEntity = userImageEntity.getUser();
        UserDto userDto = userImageDto.getUser();
        assertThat(userEntity.getId()).isEqualTo(userDto.getId());

        //map back from entity to dto
        UserImageDto mappedBackUserImageDto = userImageMapper.mapTo(userImageEntity);

        //assertions on dto after reverse mapping
        assertThat(mappedBackUserImageDto).isNotNull();
        assertThat(mappedBackUserImageDto.getId()).isEqualTo(userImageEntity.getId());
        assertThat(mappedBackUserImageDto.getPath()).isEqualTo(userImageEntity.getPath());
        assertThat(mappedBackUserImageDto.getCreatedAt()).isEqualTo(userImageEntity.getCreatedAt());

        //re-check user mapping after reverse mapping
        UserDto mappedUserDto = mappedBackUserImageDto.getUser();
        assertThat(mappedUserDto.getId()).isEqualTo(userEntity.getId());
    }

    @Test
    public void userSavedPostMapperTest() {
        //prepare test data
        UserSavedPostDto userSavedPostDto = TestDataUtil.userSavedPostA();

        //map from dto to entity
        UserSavedPost userSavedPostEntity = userSavedPostMapper.mapFrom(userSavedPostDto);

        //assertions on entity after mapping
        assertThat(userSavedPostEntity).isNotNull();
        assertThat(userSavedPostEntity.getId()).isEqualTo(userSavedPostDto.getId());

        //check user mapping
        User userEntity = userSavedPostEntity.getUser();
        UserDto userDto = userSavedPostDto.getUser();
        assertThat(userEntity.getId()).isEqualTo(userDto.getId());

        //check post mapping
        Post postEntity = userSavedPostEntity.getPost();
        PostDto postDto = userSavedPostDto.getPost();
        assertThat(postEntity.getId()).isEqualTo(postDto.getId());

        //check created at
        assertThat(userSavedPostEntity.getCreatedAt()).isEqualTo(userSavedPostDto.getCreatedAt());

        //map back from entity to dto
        UserSavedPostDto mappedBackUserSavedPostDto = userSavedPostMapper.mapTo(userSavedPostEntity);

        //assertions on dto after reverse mapping
        assertThat(mappedBackUserSavedPostDto).isNotNull();
        assertThat(mappedBackUserSavedPostDto.getId()).isEqualTo(userSavedPostEntity.getId());

        //re-check user and post mapping after reverse mapping
        assertThat(mappedBackUserSavedPostDto.getUser().getId()).isEqualTo(userEntity.getId());
        assertThat(mappedBackUserSavedPostDto.getPost().getId()).isEqualTo(postEntity.getId());
        assertThat(mappedBackUserSavedPostDto.getCreatedAt()).isEqualTo(userSavedPostEntity.getCreatedAt());
    }

    @Test
    public void postReportMapperTest() {
        //prepare test data
        PostReportDto postReportDto = TestDataUtil.postReportA();

        //map from dto to entity
        PostReport postReportEntity = postReportMapper.mapFrom(postReportDto);

        //assertions on entity after mapping
        assertThat(postReportEntity).isNotNull();
        assertThat(postReportEntity.getId()).isEqualTo(postReportDto.getId());
        assertThat(postReportEntity.getReportType()).isEqualTo(postReportDto.getReportType());

        //check post mapping
        Post postEntity = postReportEntity.getPost();
        PostDto postDto = postReportDto.getPost();
        assertThat(postEntity.getId()).isEqualTo(postDto.getId());

        //check user mapping
        User userEntity = postReportEntity.getUser();
        UserDto userDto = postReportDto.getUser();
        assertThat(userEntity.getId()).isEqualTo(userDto.getId());

        //check created at
        assertThat(postReportEntity.getCreatedAt()).isEqualTo(postReportDto.getCreatedAt());

        //map back from entity to dto
        PostReportDto mappedBackPostReportDto = postReportMapper.mapTo(postReportEntity);

        //assertions on dto after reverse mapping
        assertThat(mappedBackPostReportDto).isNotNull();
        assertThat(mappedBackPostReportDto.getId()).isEqualTo(postReportEntity.getId());
        assertThat(mappedBackPostReportDto.getReportType()).isEqualTo(postReportEntity.getReportType());

        //re-check post and user mapping after reverse mapping
        assertThat(mappedBackPostReportDto.getPost().getId()).isEqualTo(postEntity.getId());
        assertThat(mappedBackPostReportDto.getUser().getId()).isEqualTo(userEntity.getId());
        assertThat(mappedBackPostReportDto.getCreatedAt()).isEqualTo(postReportEntity.getCreatedAt());
    }

    @Test
    public void postCategoryMapperTest() {
        //prepare test data
        PostCategoryDto postCategoryDto = TestDataUtil.postCategoryA();

        //map from DTO to entity
        PostCategory postCategoryEntity = postCategoryMapper.mapFrom(postCategoryDto);

        //assertions on entity after mapping
        assertThat(postCategoryEntity).isNotNull();
        assertThat(postCategoryEntity.getId()).isEqualTo(postCategoryDto.getId());
        assertThat(postCategoryEntity.getName()).isEqualTo(postCategoryDto.getName());

        //map back from entity to DTO
        PostCategoryDto mappedBackPostCategoryDto = postCategoryMapper.mapTo(postCategoryEntity);

        //assertions on DTO after reverse mapping
        assertThat(mappedBackPostCategoryDto).isNotNull();
        assertThat(mappedBackPostCategoryDto.getId()).isEqualTo(postCategoryEntity.getId());
        assertThat(mappedBackPostCategoryDto.getName()).isEqualTo(postCategoryEntity.getName());
    }

}
