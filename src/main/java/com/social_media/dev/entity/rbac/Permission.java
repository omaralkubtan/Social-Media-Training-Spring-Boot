package com.social_media.dev.entity.rbac;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "permissions")
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Setter
    @Column(nullable = false, unique = true)
    private String code;

    @ManyToMany(mappedBy = "permissions")
    private final Set<Role> roles = new HashSet<>();

    @Override
    public String getAuthority() {
        return code;
    }

}
