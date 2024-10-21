package com.burglak.linker;

import com.burglak.linker.dto.*;
import com.burglak.linker.model.enums.*;
import lombok.experimental.UtilityClass;
import java.sql.Timestamp;
import java.time.Instant;

@UtilityClass
public class TestDataUtil {

    public UserDto userA() {
        return UserDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phone("123456789")
                .password("password")
                .bio("Software Engineer")
                .profilePicturePath("/images/profile/john.png")
                .userRole(UserRole.USER)
                .isActive(true)
                .createdAt(Timestamp.from(Instant.now()))
                .lastLogin(Timestamp.from(Instant.now()))
                .build();
    }

    public UserDto userB() {
        return UserDto.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .email("jane.smith@example.com")
                .phone("987654321")
                .password("password")
                .bio("Graphic Designer")
                .profilePicturePath("/images/profile/jane.png")
                .userRole(UserRole.ADMIN)
                .isActive(true)
                .createdAt(Timestamp.from(Instant.now()))
                .lastLogin(Timestamp.from(Instant.now()))
                .build();
    }

    public UserDto userC() {
        return UserDto.builder()
                .id(3L)
                .firstName("Alice")
                .lastName("Johnson")
                .email("alice.johnson@example.com")
                .phone("456789123")
                .password("password")
                .bio("Product Manager")
                .profilePicturePath("/images/profile/alice.png")
                .userRole(UserRole.USER)
                .isActive(true)
                .createdAt(Timestamp.from(Instant.now()))
                .lastLogin(Timestamp.from(Instant.now()))
                .build();
    }

    public FollowDto followA() {
        return FollowDto.builder()
                .id(1L)
                .follower(userA())
                .followed(userB())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public FollowDto followB() {
        return FollowDto.builder()
                .id(2L)
                .follower(userB())
                .followed(userC())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public FollowDto followC() {
        return FollowDto.builder()
                .id(3L)
                .follower(userC())
                .followed(userA())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }


    public FriendDto friendA() {
        return FriendDto.builder()
                .id(1L)
                .userOne(userA())
                .userTwo(userB())
                .isAccepted(true)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public FriendDto friendB() {
        return FriendDto.builder()
                .id(2L)
                .userOne(userB())
                .userTwo(userC())
                .isAccepted(true)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public FriendDto friendC() {
        return FriendDto.builder()
                .id(3L)
                .userOne(userC())
                .userTwo(userA())
                .isAccepted(false)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public MessageDto messageA() {
        return MessageDto.builder()
                .id(1L)
                .sender(userA())
                .recipient(userB())
                .content("Hello, how are you?")
                .isRead(false)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public MessageDto messageB() {
        return MessageDto.builder()
                .id(2L)
                .sender(userB())
                .recipient(userC())
                .content("Are you coming to the meeting?")
                .isRead(false)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public MessageDto messageC() {
        return MessageDto.builder()
                .id(3L)
                .sender(userC())
                .recipient(userA())
                .content("Don't forget our dinner!")
                .isRead(false)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public PostCategoryDto postCategoryA() {
        return PostCategoryDto.builder()
                .id(1L)
                .name("Technology")
                .build();
    }

    public PostCategoryDto postCategoryB() {
        return PostCategoryDto.builder()
                .id(2L)
                .name("Art")
                .build();
    }

    public PostCategoryDto postCategoryC() {
        return PostCategoryDto.builder()
                .id(3L)
                .name("Lifestyle")
                .build();
    }

    public PostDto postA() {
        return PostDto.builder()
                .id(1L)
                .user(userA())
                .upvotes(100L)
                .downvotes(5L)
                .postVisibility(PostVisibility.PUBLIC)
                .category(postCategoryA())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public PostDto postB() {
        return PostDto.builder()
                .id(2L)
                .user(userB())
                .upvotes(50L)
                .downvotes(2L)
                .postVisibility(PostVisibility.FRIENDS)
                .category(postCategoryB())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public PostDto postC() {
        return PostDto.builder()
                .id(3L)
                .user(userC())
                .upvotes(75L)
                .downvotes(3L)
                .postVisibility(PostVisibility.PUBLIC)
                .category(postCategoryC())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public PostImageDto postImageA(){
        return PostImageDto.builder()
                .id(1L)
                .post(postA())
                .imagePath("/images/post/postA.png")
                .createdAt(new Timestamp(1000))
                .isPrimary(false)
                .build();
    }

    public PostImageDto postImageB(){
        return PostImageDto.builder()
                .id(1L)
                .post(postA())
                .imagePath("/images/post/postB.png")
                .createdAt(new Timestamp(2000))
                .isPrimary(false)
                .build();
    }

    public PostImageDto postImageC(){
        return PostImageDto.builder()
                .id(1L)
                .post(postA())
                .imagePath("/images/post/postC.png")
                .createdAt(new Timestamp(3000))
                .isPrimary(false)
                .build();
    }

    public PostCommentDto postCommentA() {
        return PostCommentDto.builder()
                .id(1L)
                .post(postA())
                .user(userA())
                .content("This is a sample comment.")
                .parentComment(null)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public PostCommentDto postCommentB() {
        return PostCommentDto.builder()
                .id(2L)
                .post(postB())
                .user(userB())
                .content("Great post!")
                .parentComment(postCommentA())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public PostCommentDto postCommentC() {
        return PostCommentDto.builder()
                .id(3L)
                .post(postC())
                .user(userC())
                .content("Thanks for sharing!")
                .parentComment(null)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    // Przykładowe raporty postów
    public PostReportDto postReportA() {
        return PostReportDto.builder()
                .id(1L)
                .post(postA())
                .user(userB())
                .reportType("Spam")
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public PostReportDto postReportB() {
        return PostReportDto.builder()
                .id(2L)
                .post(postB())
                .user(userC())
                .reportType("Inappropriate Content")
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public PostReportDto postReportC() {
        return PostReportDto.builder()
                .id(3L)
                .post(postC())
                .user(userA())
                .reportType("Harassment")
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public PostReactionDto postReactionA() {
        return PostReactionDto.builder()
                .id(1L)
                .post(postA())
                .user(userB())
                .postReactionType(PostReactionType.LIKE)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public PostReactionDto postReactionB() {
        return PostReactionDto.builder()
                .id(2L)
                .post(postB())
                .user(userC())
                .postReactionType(PostReactionType.DISLIKE)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public PostReactionDto postReactionC() {
        return PostReactionDto.builder()
                .id(3L)
                .post(postC())
                .user(userA())
                .postReactionType(PostReactionType.LIKE)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public UserSettingsDto userSettingsA() {
        return UserSettingsDto.builder()
                .id(1L)
                .user(userA())
                .showFriends(true)
                .showProfilePicture(true)
                .showBio(true)
                .showPosts(true)
                .showLikes(true)
                .showComments(true)
                .createdAt(Timestamp.from(Instant.now()))
                .updatedAt(Timestamp.from(Instant.now()))
                .build();
    }

    public UserSettingsDto userSettingsB() {
        return UserSettingsDto.builder()
                .id(2L)
                .user(userB())
                .showFriends(false)
                .showProfilePicture(true)
                .showBio(true)
                .showPosts(false)
                .showLikes(true)
                .showComments(false)
                .createdAt(Timestamp.from(Instant.now()))
                .updatedAt(Timestamp.from(Instant.now()))
                .build();
    }

    public UserSettingsDto userSettingsC() {
        return UserSettingsDto.builder()
                .id(3L)
                .user(userC())
                .showFriends(true)
                .showProfilePicture(false)
                .showBio(true)
                .showPosts(true)
                .showLikes(false)
                .showComments(true)
                .createdAt(Timestamp.from(Instant.now()))
                .updatedAt(Timestamp.from(Instant.now()))
                .build();
    }

    public UserStatusDto userStatusA() {
        return UserStatusDto.builder()
                .id(1L)
                .user(userA())
                .isSuspended(false)
                .suspendedUntil(null)
                .isVerified(true)
                .build();
    }

    public UserStatusDto userStatusB() {
        return UserStatusDto.builder()
                .id(2L)
                .user(userB())
                .isSuspended(true)
                .suspendedUntil(Timestamp.from(Instant.now().plusSeconds(86400)))
                .isVerified(false)
                .build();
    }

    public UserStatusDto userStatusC() {
        return UserStatusDto.builder()
                .id(3L)
                .user(userC())
                .isSuspended(false)
                .suspendedUntil(null)
                .isVerified(true)
                .build();
    }

    public UserImageDto userImageA() {
        return UserImageDto.builder()
                .id(1L)
                .user(userA())
                .path("/images/users/john_image1.png")
                .userImageVisibility(UserImageVisibility.PUBLIC)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public UserImageDto userImageB() {
        return UserImageDto.builder()
                .id(2L)
                .user(userB())
                .path("/images/users/jane_image1.png")
                .userImageVisibility(UserImageVisibility.FRIENDS)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public UserImageDto userImageC() {
        return UserImageDto.builder()
                .id(3L)
                .user(userC())
                .path("/images/users/alice_image1.png")
                .userImageVisibility(UserImageVisibility.PUBLIC)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public UserSavedPostDto userSavedPostA() {
        return UserSavedPostDto.builder()
                .id(1L)
                .user(userA())
                .post(postA())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public UserSavedPostDto userSavedPostB() {
        return UserSavedPostDto.builder()
                .id(2L)
                .user(userB())
                .post(postB())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public UserSavedPostDto userSavedPostC() {
        return UserSavedPostDto.builder()
                .id(3L)
                .user(userC())
                .post(postC())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

}