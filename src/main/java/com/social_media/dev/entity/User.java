package com.social_media.dev.entity;

import com.social_media.dev.entity.rbac.Permission;
import com.social_media.dev.entity.rbac.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(unique=true)
    private String username;

    @Setter
    @Column(unique=true)
    private String email;

    @Setter
//    @Pattern(regexp = Constants.PASSWORD_VALIDATION_REGEXP, message = "password must contains at least 8 characters, 1 number, and 1 special character")
    private String password;

    @Getter
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Where(clause = "deleted_at is null")
    protected Set<Role> roles = new HashSet<>();



    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean can(String permissionCode) {
        return getAuthorities()
                .stream()
                .anyMatch(p -> p.getCode().equals(permissionCode));
    }


    @Override
    public Collection<Permission> getAuthorities() {
        var authorities = new HashSet<Permission>();

        roles.forEach(role -> authorities.addAll(role.getPermissions()));

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}