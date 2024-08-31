package com.social_media.dev.entity;

import com.social_media.dev.util.helper.Context;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @CreatedDate
    protected LocalDateTime createdAt;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id")
    protected User createdBy;

    @LastModifiedDate
    protected LocalDateTime updatedAt;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_user_id")
    protected User updatedBy;

    @Column
    protected LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deleted_by_user_id")
    protected User deletedBy;

    public boolean getIsDeleted() {
        return deletedAt != null;
    }


    @PrePersist
    public void prepare() {
        this.createdAt = LocalDateTime.now();
        this.createdBy = Context.getCurrentUser();
    }

    @PreUpdate
    public void refresh() {
        updatedBy = Context.getCurrentUser();
        updatedAt = LocalDateTime.now();
    }

    /**
     * For Soft Delete
     */
    public void delete() {
        deletedBy = Context.getCurrentUser();
        deletedAt = LocalDateTime.now();
    }

}
