package com.social_media.dev.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Entity
@Getter
@Table(name = "comments")
public class Comment extends BaseEntity {

    @ManyToOne
    private Post post;

    private String content;
}