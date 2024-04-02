package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PostRepository interface. This interface extends JpaRepository and provides methods for
 * performing CRUD operations on Post objects. JpaRepository is a JPA specific extension of
 * Repository which provides some additional methods to retrieve entities using the pagination and
 * sorting abstraction.
 */
public interface PostRepository extends JpaRepository<Post, Integer> {

  /**
   * Custom query method to find posts by user id and order them by time in descending order.
   *
   * @param id The id of the user.
   * @param pageable The pagination information.
   * @return A page of Post objects.
   */
  Page<Post> findByUserIdOrderByTimeDesc(Integer id, Pageable pageable);

  /**
   * Custom query method to find public posts by user id and order them by time in descending order.
   *
   * @param id The id of the user.
   * @param pageable The pagination information.
   * @return A page of Post objects.
   */
  Page<Post> findByUserIdAndPrivatePostFalseOrderByTimeDesc(Integer id, Pageable pageable);

  /**
   * Custom query method to find distinct posts by user's friends id or user id and order them by
   * time in descending order.
   *
   * @param id The id of the user's friend.
   * @param id2 The id of the user.
   * @param pageable The pagination information.
   * @return A page of Post objects.
   */
  Page<Post> findDistinctByUserFriendsIdOrUserIdOrderByTimeDesc(
      Integer id, Integer id2, Pageable pageable);
}
