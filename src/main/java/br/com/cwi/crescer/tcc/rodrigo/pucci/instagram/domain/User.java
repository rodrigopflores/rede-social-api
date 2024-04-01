package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "tcc_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "profile_pic")
    private String profilePic;

    @ManyToMany(mappedBy = "likes")
    private List<Post> likedPosts;

    private String password;


    @ManyToMany
    @JoinTable(
            name = "tcc_friendship",
            joinColumns = @JoinColumn(name = "friend_one_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_two_id"))
    private List<User> friends;

    @ManyToMany(mappedBy = "friends")
    private List<User> friendOf;

    @ManyToMany
    @JoinTable(
            name = "tcc_friend_request",
            joinColumns = @JoinColumn(name = "sender_id"),
            inverseJoinColumns = @JoinColumn(name = "receiver_id"))
    private List<User> friendRequestsSent;

    @ManyToMany(mappedBy = "friendRequestsSent")
    private List<User> friendRequestsReceived;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()->"ROLE_USER");
    }
}
