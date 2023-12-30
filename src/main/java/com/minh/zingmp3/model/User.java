package com.minh.zingmp3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class User  implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String useName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column
    private String avatar;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
    private List<PlayList> playLists=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
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

    public User(String useName, String email, String password, String name, Role role, List<PlayList> playLists,String img) {
        this.useName = useName;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.playLists = playLists;
        this.avatar=img;
    }
}
