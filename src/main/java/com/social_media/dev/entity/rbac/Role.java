package com.social_media.dev.entity.rbac;


import com.social_media.dev.entity.BaseEntity;
import com.social_media.dev.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Setter
    @NotBlank(message = "Role name must not be null")
    private String name;

    @Setter
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "permission_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(mappedBy = "roles")
    private final Set<User> users = new HashSet<>();

}
