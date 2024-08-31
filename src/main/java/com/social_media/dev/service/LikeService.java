package com.social_media.dev.service;

import com.social_media.dev.entity.Like;
import com.social_media.dev.entity.Post;
import com.social_media.dev.entity.User;
import com.social_media.dev.error.exceptions.BadRequestException;
import com.social_media.dev.repository.LikeRepository;
import com.social_media.dev.repository.PostRepository;
import com.social_media.dev.repository.UserRepository;
import com.social_media.dev.util.helper.Context;
import com.social_media.dev.util.localization.Tokens;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class LikeService {

    private LikeRepository likeRepository;

    private PostRepository postRepository;


    @Caching(evict = {
            @CacheEvict(value = "posts", key = "#postId"),
            @CacheEvict(value = "feed", allEntries = true)
    })
    @Transactional
    public void likePost(Long postId) {
        var user = Context.getCurrentUser();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        if (likeRepository.existsByCreatedByAndPost(user, post)) {
            throw new BadRequestException(Tokens.M_POST_ALREADY_LIKED);
        }

        Like like = new Like();
        like.setPost(post);
        likeRepository.save(like);

        postRepository.save(post);
    }
}