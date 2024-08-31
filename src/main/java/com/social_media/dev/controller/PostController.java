package com.social_media.dev.controller;

import com.social_media.dev.dto.CommentRequestDto;
import com.social_media.dev.dto.CommentResponseDto;
import com.social_media.dev.dto.PostRequestDto;
import com.social_media.dev.dto.PostResponseDto;
import com.social_media.dev.entity.Comment;
import com.social_media.dev.service.CommentService;
import com.social_media.dev.service.LikeService;
import com.social_media.dev.service.PostService;
import com.social_media.dev.util.helper.Constants;
import com.social_media.dev.util.helper.PaginationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController extends ApiController {

    private PostService postService;

    private CommentService commentService;

    private LikeService likeService;


    @Operation(
            summary = "Create Post",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping("/create")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto) {
        var createdPost = postService.createPost(postRequestDto);
        return new ResponseEntity<>(modelMapper.map(createdPost, PostResponseDto.class), HttpStatus.CREATED);
    }


    @Cacheable(value = "posts", key = "#id")
    @Operation(
            summary = "Get Post By Id",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        var post = postService.getPostById(id);
        return ResponseEntity.ok(modelMapper.map(post, PostResponseDto.class));
    }


    @Cacheable(value = "feed", key = "{#page, #pageSize}")
    @Operation(
            summary = "Get Feed",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/")
    public ResponseEntity<PaginationResult<PostResponseDto>> getFeed(
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE)
            @PositiveOrZero
            Integer page,

            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE)
            @Positive
            Integer pageSize
    ) {
        var posts = postService.getFeed(page, pageSize);
        return ResponseEntity.ok(posts.mapTo(modelMapper, PostResponseDto.class));
    }


    @Operation(
            summary = "Like Post",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable Long postId) {
        likeService.likePost(postId);
        return ResponseEntity.ok("Post liked successfully");
    }


    @Operation(
            summary = "Comment On Post",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping("/{postId}/comment")
    public ResponseEntity<CommentResponseDto> addComment(@PathVariable Long postId,
                                                         @RequestBody CommentRequestDto commentRequestDto,
                                                         @AuthenticationPrincipal UserDetails userDetails) {
        Comment comment = commentService.addComment(postId, userDetails.getUsername(), commentRequestDto.getContent());
        return new ResponseEntity<>(modelMapper.map(comment, CommentResponseDto.class), HttpStatus.CREATED);
    }


    @Cacheable(value = "comments", key = "{#postId, #page, #pageSize}")
    @Operation(
            summary = "Get Posts comments",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/{postId}/comments")
    public ResponseEntity<PaginationResult<CommentResponseDto>> getCommentsByPost(
            @PathVariable
            Long postId,

            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE)
            @PositiveOrZero
            Integer page,

            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE)
            @Positive
            Integer pageSize
    ) {
        var comments = commentService.getCommentsByPost(postId, page, pageSize);
        return ResponseEntity.ok(comments.mapTo(modelMapper, CommentResponseDto.class));
    }
}