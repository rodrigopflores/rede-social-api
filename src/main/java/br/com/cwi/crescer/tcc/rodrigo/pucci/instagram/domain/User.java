package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User entity class. This class represents a user in the system. It implements UserDetails for
 * Spring Security integration.
 */
@Data
@Entity
@Table(name = "tcc_user")
public class User implements UserDetails {

  /** The unique identifier of the user. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /** The first name of the user. */
  @Column(name = "first_name")
  private String firstName;

  /** The last name of the user. */
  @Column(name = "last_name")
  private String lastName;

  /** The email of the user. Used for authentication. */
  @Column(name = "email")
  private String email;

  /** The nickname of the user. */
  @Column(name = "nick_name")
  private String nickName;

  /** The date of birth of the user. */
  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  /** The profile picture of the user. */
  @Column(name = "profile_pic")
  private String profilePic;

  /** The posts liked by the user. */
  @ManyToMany(mappedBy = "likes")
  private List<Post> likedPosts;

  /** The password of the user. Used for authentication. */
  private String password;

  /** The friends of the user. */
  @ManyToMany
  @JoinTable(
      name = "tcc_friendship",
      joinColumns = @JoinColumn(name = "friend_one_id"),
      inverseJoinColumns = @JoinColumn(name = "friend_two_id"))
  private List<User> friends;

  /** The users who are friends of this user. */
  @ManyToMany(mappedBy = "friends")
  private List<User> friendOf;

  /** The friend requests sent by the user. */
  @ManyToMany
  @JoinTable(
      name = "tcc_friend_request",
      joinColumns = @JoinColumn(name = "sender_id"),
      inverseJoinColumns = @JoinColumn(name = "receiver_id"))
  private List<User> friendRequestsSent;

  /** The friend requests received by the user. */
  @ManyToMany(mappedBy = "friendRequestsSent")
  private List<User> friendRequestsReceived;

  /** The posts made by the user. */
  @OneToMany(mappedBy = "user")
  private List<Post> posts;

  /**
   * Returns the username for Spring Security integration. In this case, the email is used as the
   * username.
   */
  @Override
  public String getUsername() {
    return email;
  }

  /** Returns whether the account is not expired for Spring Security integration. */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /** Returns whether the account is not locked for Spring Security integration. */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /** Returns whether the credentials are not expired for Spring Security integration. */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /** Returns whether the account is enabled for Spring Security integration. */
  @Override
  public boolean isEnabled() {
    return true;
  }

  /**
   * Returns the authorities granted to the user for Spring Security integration. In this case, all
   * users have the "ROLE_USER" authority.
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(() -> "ROLE_USER");
  }
}
