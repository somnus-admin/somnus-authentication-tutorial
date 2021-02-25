package com.somnus.firebasedemoproject.users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "password")
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RoleEntity> authorities;

    @Column(name = "enabled")
    private final boolean enabled = true;

    @Column(name = "credentials_non_expired")
    private final boolean credentialsNonExpired = true;

    @Column(name = "account_non_locked")
    private final boolean accountNonLocked = true;

    @Column(name = "account_non_expired")
    private final boolean accountNonExpired = true;


    public User() {
    }

    public User(String username, String email, String displayName,
                List<RoleEntity> roles) {
        this.username = username;
        this.email = email;
        this.displayName = displayName;
        this.authorities = roles;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<RoleEntity> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
