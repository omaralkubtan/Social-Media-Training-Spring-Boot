package com.social_media.dev.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
@Getter
@Table(name = "likes")
public class Like extends BaseEntity {

    @ManyToOne
    private Post post;
}