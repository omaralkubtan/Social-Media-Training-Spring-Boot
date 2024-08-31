package com.social_media.dev.service;

import com.social_media.dev.dto.CommentResponseDto;
import com.social_media.dev.entity.Comment;
import com.social_media.dev.entity.Post;
import com.social_media.dev.entity.User;
import com.social_media.dev.repository.CommentRepository;
import com.social_media.dev.repository.PostRepository;
import com.social_media.dev.repository.UserRepository;
import com.social_media.dev.util.helper.PaginationResult;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;

    private PostRepository postRepository;

    private UserRepository userRepository;

    @CacheEvict(value = {"feed", "comments"}, allEntries = true)
    @Transactional
    public Comment addComment(Long postId, String username, String content) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setContent(content);

        commentRepository.save(comment);

        return comment;
    }

    public PaginationResult<Comment> getCommentsByPost(Long postId, int page, int pageSize) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Comment> comments = commentRepository.findByPost(post, pageable);
        return new PaginationResult<>(comments);
    }
}