package com.zxytech.wechat.domain.account;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xwxia
 * @date 2018/3/6 11:06
 */
@Document(collection = "manager")
public class Manager implements UserDetails {
    @Id
    private String id;
    private String name;
    private String phone;
    private String email;
    private String username;
    private String password;
    private UserRoleEnum role;
    private Long createAt;
    private Long updateAt;
    private Long lastLoginAt;
    private boolean active;

    public Manager() {
        this.createAt = System.currentTimeMillis();
    }

    public Manager(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public Manager(String name, String phone, String email, String username, String password, UserRoleEnum userRole) {
        this(username, password);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = userRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    public Long getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Long lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", lastLoginAt=" + lastLoginAt +
                ", active=" + active +
                '}';
    }
}
