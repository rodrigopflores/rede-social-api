package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/** Post entity class. This class represents a post made by a user. */
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tcc_post")
public class Post {

  /** The unique identifier of the post. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /** The user who made the post. */
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  /** The message content of the post. */
  @Column(name = "message")
  private String message;

  /** The image associated with the post. */
  @Column(name = "image")
  private String image;

  /** The time when the post was made. */
  @Column(name = "post_time")
  private LocalDateTime time;

  /** A flag indicating whether the post is private or not. */
  @Column(name = "is_private")
  private boolean privatePost;

  /** The list of users who liked the post. */
  @ManyToMany
  @JoinTable(
      name = "tcc_likes",
      joinColumns = @JoinColumn(name = "post_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> likes;
}
