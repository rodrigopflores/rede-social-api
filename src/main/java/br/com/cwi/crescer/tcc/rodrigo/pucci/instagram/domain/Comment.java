package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/** Comment entity class. This class represents a comment made by a user on a post. */
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tcc_comment")
public class Comment {

  /** The unique identifier of the comment. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /** The post on which the comment was made. */
  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;

  /** The user who made the comment. */
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User commenter;

  /** The time when the comment was made. */
  @Column(name = "cmt_time")
  private LocalDateTime time;

  /** The content of the comment. */
  @Column(name = "cmt_content")
  private String content;
}
