package com.social_media.dev.service;

import com.social_media.dev.dto.PostRequestDto;
import com.social_media.dev.dto.PostResponseDto;
import com.social_media.dev.entity.Post;
import com.social_media.dev.entity.User;
import com.social_media.dev.repository.PostRepository;
import com.social_media.dev.repository.UserRepository;
import com.social_media.dev.util.helper.PaginationResult;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;


    @CacheEvict(value = "feed", allEntries = true)
    @Transactional
    public Post createPost(PostRequestDto postRequestDto) {
        Post post = new Post();
        post.setContent(postRequestDto.getContent());

        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

    }

    public PaginationResult<Post> getFeed(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Post> postsPage = postRepository.findAll(pageable);

        return new PaginationResult<>(postsPage);
    }
}