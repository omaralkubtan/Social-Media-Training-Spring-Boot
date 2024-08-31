package com.social_media.dev.repository;

import com.social_media.dev.entity.Like;
import com.social_media.dev.entity.Post;
import com.social_media.dev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByCreatedByAndPost(User user, Post post);
}