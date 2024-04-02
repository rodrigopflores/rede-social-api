package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CommentRepository interface. This interface extends JpaRepository and provides methods for
 * performing CRUD operations on Comment objects. JpaRepository is a JPA specific extension of
 * Repository which provides some additional methods to retrieve entities using the pagination and
 * sorting abstraction.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

  /**
   * Custom query method to find comments by post id and order them by time in ascending order.
   *
   * @param id The id of the post.
   * @param pageable The pagination information.
   * @return A page of Comment objects.
   */
  Page<Comment> findByPostIdOrderByTimeAsc(Integer id, Pageable pageable);
}
